package com.github.taccisum.domain.core.exception.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.4
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ErrorCode {
    String value();

    String description() default "";

    boolean inherited() default false;
}
