package com.github.taccisum.domain.core.autoconfigure;

import com.github.taccisum.domain.core.DependenciesManager;
import com.github.taccisum.domain.core.common.dict.Dict;
import com.github.taccisum.domain.core.common.dict.impl.SimpleDict;
import com.github.taccisum.domain.core.exception.ErrorCodeMapping;
import com.github.taccisum.domain.core.exception.ErrorCodeService;
import com.github.taccisum.domain.core.plugin.ExtensibleFactory;
import com.github.taccisum.domain.core.spring.FactoryAspect;
import com.github.taccisum.domain.core.spring.SpringBasedDependenciesManager;
import com.github.taccisum.domain.core.utils.DictUtils;
import com.github.taccisum.domain.core.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
@Slf4j
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
    public DependenciesManager domainDependenciesManager() {
        return new SpringBasedDependenciesManager(context);
    }

    @Bean
    @ConditionalOnMissingBean
    public ExtensibleFactory extensibleFactory(DependenciesManager dependenciesManager) {
        ExtensibleFactory factory = new ExtensibleFactory(dependenciesManager);
        factory.setApplicationContext(context);
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean
    public Dict domainDict(@Autowired(required = false) List<SimpleDict.DataInitializer> initializers) {
        SimpleDict dict = new SimpleDict(initializers);
        DictUtils.setDict(dict);
        return dict;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SpringUtils.setContext(context);
    }
}
