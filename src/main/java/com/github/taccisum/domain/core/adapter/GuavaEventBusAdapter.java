package com.github.taccisum.domain.core.adapter;

import com.github.taccisum.domain.core.Event;
import com.github.taccisum.domain.core.EventBus;
import com.github.taccisum.domain.core.EventSubscriber;

/**
 * Guava EventBus 适配器
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
public class GuavaEventBusAdapter implements EventBus {
    private com.google.common.eventbus.EventBus delegate;

    public GuavaEventBusAdapter(com.google.common.eventbus.EventBus delegate) {
        this.delegate = delegate;
    }

    @Override
    public void post(Event event) {
        this.delegate.post(event);
    }

    @Override
    public void register(EventSubscriber eventSubscriber) {
        this.delegate.register(eventSubscriber);
    }
}
