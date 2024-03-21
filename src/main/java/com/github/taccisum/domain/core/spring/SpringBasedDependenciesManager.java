package com.github.taccisum.domain.core.spring;

import com.github.taccisum.domain.core.DependenciesManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2024/3/21
 */
@Slf4j
public class SpringBasedDependenciesManager implements DependenciesManager {
    private ApplicationContext context;

    public SpringBasedDependenciesManager(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <T> T findOne(Class<T> clazz) {
        return context.getBean(clazz);
    }

    @Override
    public <T> T tryFindOne(Class<T> clazz) {
        Collection<T> beans = context.getBeansOfType(clazz).values();
        if (beans.isEmpty()) {
//                    log.warn("Not any beans of class {} found. Please ensure it won't cause error by yourself.", clazz.getName());
            return null;
        }
        T first = beans.iterator().next();
        if (beans.size() > 1) {
//                    log.warn("Found {} beans of class {}. Would use first one({}) by default. Please ensure it won't cause error by yourself.",
//                            beans.size(), clazz.getName(), first);
        }
        return first;
    }
}
