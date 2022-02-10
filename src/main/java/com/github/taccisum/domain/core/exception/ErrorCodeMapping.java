package com.github.taccisum.domain.core.exception;

import com.github.taccisum.domain.core.DomainException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.4
 */
public interface ErrorCodeMapping {
    default ErrorCode map(DomainException e) {
        return this.map(e.getClass());
    }

    /**
     * map 异常类型为错误码
     */
    ErrorCode map(Class<? extends DomainException> clazz);

    class Default implements ErrorCodeMapping {
        private Map<Class<? extends DomainException>, ErrorCode> mapping = new HashMap<>();

        {
            mapping.put(DomainException.class, new ErrorCode.Default("DOMAIN_ERROR", "业务错误"));
            mapping.put(DataErrorException.class, new ErrorCode.Default("DATA_ERROR", "目标数据有误"));
            mapping.put(DataNotFoundException.class, new ErrorCode.Default("DATA_NOT_FOUND", "目标数据不存在"));
        }

        public void link(Class<? extends DomainException> clazz, ErrorCode code) {
            mapping.put(clazz, code);
        }

        public void link(Class<? extends DomainException> clazz, String code) {
            this.link(clazz, new ErrorCode.Default(code));
        }

        /**
         * {@inheritDoc}
         * <p>
         * 支持通过 {@link #link} 手动添加映射关系，也可以通过 {@link com.github.taccisum.domain.core.exception.annotation.ErrorCode} 注解标记异常类进行映射
         */
        @Override
        public ErrorCode map(DomainException e) {
            return map(e.getClass());
        }

        @Override
        public ErrorCode map(Class<? extends DomainException> clazz) {
            ErrorCode code = mapping.get(clazz);
            if (code == null) {
                code = this.tryFindFromAnnotation(clazz);

                if (code == null) {
                    // try find from super class
                    Class<?> sc = clazz.getSuperclass();

                    if (DomainException.class.isAssignableFrom(sc)) {
                        return this.map((Class<? extends DomainException>) sc);
                    } else {
                        return null;
                    }
                }
            }
            return code;
        }

        private ErrorCode tryFindFromAnnotation(Class<? extends DomainException> clazz) {
            com.github.taccisum.domain.core.exception.annotation.ErrorCode annotation = clazz.getAnnotation(com.github.taccisum.domain.core.exception.annotation.ErrorCode.class);

            if (annotation == null) {
                return null;
            }

            ErrorCode.Default code = new ErrorCode.Default(annotation.value(), annotation.description())
                    .inherited(annotation.inherited());
            mapping.put(clazz, code);
            return code;
        }
    }
}
