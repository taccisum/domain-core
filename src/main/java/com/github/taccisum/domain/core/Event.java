package com.github.taccisum.domain.core;

import lombok.Getter;
import lombok.Setter;

/**
 * 领域事件
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
public interface Event<T extends EventPublisher> {
    /**
     * 设置事件的发布者
     *
     * @param publisher 发布者
     */
    void setPublisher(Object publisher);

    /**
     * 获取事件的发布者
     *
     * @return 此事件的发布者
     */
    Object getPublisher();

    abstract class Base<T extends EventPublisher> implements Event<T> {
        @Setter
        @Getter
        private Object publisher;
    }
}
