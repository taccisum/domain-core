package com.github.taccisum.domain.core;

import lombok.Getter;

/**
 * 实体
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
public interface Entity<ID> {
    /**
     * 获取实体唯一标识
     */
    ID getId();

    /**
     * 获取实体唯一标识
     *
     * @see #getId()
     */
    default ID id() {
        return this.getId();
    }

    abstract class Base<ID> extends EventPublisher.Base implements Entity<ID> {
        @Getter
        private ID id;

        public Base(ID id) {
            this.id = id;
        }
    }
}
