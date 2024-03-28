package com.github.taccisum.domain.core;

/**
 * 领域事件总线
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
public interface EventBus {
    /**
     * 通过当前事件总线发布一个事件
     *
     * @param event 领域事件
     */
    void post(Event event);

    /**
     * @since 2024-03-28
     */
    void register(EventSubscriber subscriber);
}
