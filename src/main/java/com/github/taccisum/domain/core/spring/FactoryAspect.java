package com.github.taccisum.domain.core.spring;

import com.github.taccisum.domain.core.EventBus;
import com.github.taccisum.domain.core.EventBusAware;
import com.github.taccisum.domain.core.utils.SpringUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Factory 切面，为 Factory 提供 EventBus, Spring Beans 自动注入能力
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
@Slf4j
@Aspect
public class FactoryAspect {
    @Autowired(required = false)
    private EventBus eventBus;

    /**
     * 是否启用传统的扫描注解进行注入的模式（比较低效）
     */
    @Setter
    private boolean legacyInjectEnabled = false;

    @Pointcut("target(com.github.taccisum.domain.core.Factory)")
    public void factoryMethods() {
    }

    /**
     * 为构建出的实体统一添加 event bus
     */
    @Around("factoryMethods()")
    public Object setEventBus(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();

        if (result == null) return null;

        if (eventBus != null) {
            if (result instanceof EventBusAware) {
                log.debug("Inject EventBus '{}' into '{}'", eventBus, result);
                EventBusAware awarer = (EventBusAware) result;
                awarer.setEventBus(eventBus);
            } else {
                log.debug("'{}' is not instance of '{}'. Injection will be ignored", result, EventBusAware.class);
            }
        }

        // TODO:: Legacy: should be remove
        if (legacyInjectEnabled) {
            log.debug("Inject Spring Beans into '{}'", result);
            SpringUtils.inject(result);
        }

        return result;
    }
}
