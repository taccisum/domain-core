package com.github.taccisum.domain.core;

import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

/**
 * 领域事件发布者
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
public interface EventPublisher {
    /**
     * 发布一个事件
     *
     * @param event 事件
     */
    void publish(Event event);

    @Slf4j
    class Base implements EventPublisher, EventBusAware {
        private EventBus eventBus;

        @Override
        public void setEventBus(EventBus eventBus) {
            this.eventBus = eventBus;
        }

        @Override
        public void publish(Event event) {
            Class type = (Class) ((ParameterizedTypeImpl) event.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            if (!type.isAssignableFrom(this.getClass())) {
                log.warn("Publisher '{}' is not matched event '{}'. Expected publisher type of this event is '{}'", this.getClass().getName(), event.getClass().getName(), type.getName());
            }

            event.setPublisher(this);

            if (eventBus != null) {
                eventBus.post(event);
            } else {
                log.warn("Event bus of publisher '{}' has not been specified. Event '{}' will not be published!", this.getClass().getName(), event.getClass().getName());
            }
        }
    }
}
