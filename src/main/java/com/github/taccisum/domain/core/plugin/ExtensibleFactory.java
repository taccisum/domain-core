package com.github.taccisum.domain.core.plugin;

import com.github.taccisum.domain.core.DependenciesAware;
import com.github.taccisum.domain.core.DependenciesManager;
import com.github.taccisum.domain.core.Entity;
import com.github.taccisum.domain.core.Factory;
import com.github.taccisum.domain.core.exception.annotation.ErrorCode;
import lombok.Getter;
import org.pf4j.PluginManager;
import org.springframework.context.ApplicationContext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 0.5
 */
public class ExtensibleFactory implements Factory {
    private DependenciesManager dependenciesManager;
    private PluginManager pluginManager;
    private ApplicationContext applicationContext;

    public ExtensibleFactory(DependenciesManager dependenciesManager) {
        this.dependenciesManager = dependenciesManager;
    }

    public void setPluginManager(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 创建指定实体实例
     *
     * @param id       实体 id
     * @param criteria 匹配条件
     * @param type     实体工厂类型
     */
    public <ID extends Serializable, E extends Entity<ID>, C, F extends EntityFactory<ID, E, C>>
    E create(ID id, C criteria, Class<F> type) {
        List<F> factories = new ArrayList<>();
        if (this.pluginManager != null) {
            factories.addAll(pluginManager.getExtensions(type));
        }
        if (this.applicationContext != null) {
            factories.addAll(applicationContext.getBeansOfType(type).values());
        }
        // TODO:: should cache ordered result for perf optimization.
        F f0 = null;
        factories.sort(Comparator.comparingInt(EntityFactory::getOrder));
        for (F f : factories) {
            if (f.match(id, criteria)) {
                f0 = f;
                break;
            }
        }

        final F factory = f0;
        if (factory != null) {
            E e = factory.create(id, criteria);
            if (e instanceof DependenciesAware) {
                ((DependenciesAware) e).inject(dependenciesManager);
            }
            return e;
        }

        throw new NoSuitableFactoryFoundException(id, criteria, type);
    }

    @ErrorCode(value = "FACTORY", inherited = true)
    public static class FactoryExtensionsException extends UnsupportedExtensionsException {
        @Getter
        protected Object id;
        @Getter
        protected Object criteria;
        @Getter
        protected Class<? extends EntityFactory> type;

        public FactoryExtensionsException(Object id, Object criteria, Class<? extends EntityFactory> type) {
            super("");
            this.id = id;
            this.criteria = criteria;
            this.type = type;
        }

        @Override
        public String getMessage() {
            return String.format("Factory %s error for id %s, criteria: %s.", type.getSimpleName(), id, criteria);
        }
    }

    @ErrorCode(value = "NO_SUITABLE", inherited = true)
    public static class NoSuitableFactoryFoundException extends FactoryExtensionsException {
        public NoSuitableFactoryFoundException(Object id, Object criteria, Class<? extends EntityFactory> type) {
            super(id, criteria, type);
        }

        @Override
        public String getMessage() {
            return String.format(
                    "No any suitable factory found for id %s, criteria: %s. expected factory type: %s.",
                    id, criteria, type.getSimpleName()
            );
        }
    }
}