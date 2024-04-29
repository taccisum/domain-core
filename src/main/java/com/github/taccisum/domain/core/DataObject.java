package com.github.taccisum.domain.core;

import lombok.Getter;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Mark a class that implemented this interface a data object
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.5
 */
public interface DataObject<ID extends Serializable> {
    /**
     * @return Unique of relevant entity
     */
    ID getId();

    /**
     * @param id Unique of relevant entity
     */
    void setId(ID id);

    abstract class Wrapper<DO extends DataObject<?>> {
        @Getter
        protected DO data;

        public Wrapper(DO data) {
            this.data = data;
        }

        /**
         * Set up data fields directly
         */
        public void set(Consumer<DO> consumer) {
            consumer.accept(this.getData());
        }
    }
}
