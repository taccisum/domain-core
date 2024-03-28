package com.github.taccisum.domain.core.spring;

import com.github.taccisum.domain.core.EventBus;
import com.github.taccisum.domain.core.EventSubscriber;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2024/3/28
 */
public class SpringEventSubscriber implements EventSubscriber, InitializingBean {
    @Autowired
    private EventBus eventBus;

    @Override
    public void afterPropertiesSet() throws Exception {
        eventBus.register(this);
    }
}
