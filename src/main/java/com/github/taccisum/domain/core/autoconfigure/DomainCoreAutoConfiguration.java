package com.github.taccisum.domain.core.autoconfigure;

import com.github.taccisum.domain.core.exception.ErrorCodeMapping;
import com.github.taccisum.domain.core.exception.ErrorCodeService;
import com.github.taccisum.domain.core.spring.FactoryAspect;
import com.github.taccisum.domain.core.utils.SpringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
@Import(FactoryAspect.class)
public class DomainCoreAutoConfiguration implements InitializingBean {
    @Autowired
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

    @Override
    public void afterPropertiesSet() throws Exception {
        SpringUtils.setContext(context);
    }
}
