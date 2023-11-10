package com.github.taccisum.domain.core.autoconfigure;

import com.github.taccisum.domain.core.DependenciesManager;
import com.github.taccisum.domain.core.exception.ErrorCodeMapping;
import com.github.taccisum.domain.core.exception.ErrorCodeService;
import com.github.taccisum.domain.core.plugin.ExtensibleFactory;
import com.github.taccisum.domain.core.spring.FactoryAspect;
import com.github.taccisum.domain.core.utils.SpringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
@Import(FactoryAspect.class)
public class DomainCoreAutoConfiguration implements InitializingBean {
    @Resource
    private ApplicationContext context;

    @Bean
    public ErrorCodeMapping errorCodeMapping() {
        return new ErrorCodeMapping.Default();
    }

    @Bean
    public ErrorCodeService errorCodeService(ErrorCodeMapping mapping) {
        ErrorCodeService bean = new ErrorCodeService("app");
        bean.setErrorCodeMapping(mapping);
        return bean;
    }

    @Bean
    @ConditionalOnMissingBean
    public ExtensibleFactory extensibleFactory() {
        ExtensibleFactory factory = new ExtensibleFactory(new DependenciesManager() {
            @Override
            public <T> T findOne(Class<T> clazz) {
                return context.getBean(clazz);
            }
        });
        factory.setApplicationContext(context);
        return factory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SpringUtils.setContext(context);
    }
}
