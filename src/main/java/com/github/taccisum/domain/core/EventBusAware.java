package com.github.taccisum.domain.core;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
public interface EventBusAware {
    /**
     * 设置事件总线
     *
     * @param eventBus 事件总线
     */
    void setEventBus(EventBus eventBus);
}
