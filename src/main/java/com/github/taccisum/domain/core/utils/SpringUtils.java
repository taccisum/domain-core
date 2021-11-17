package com.github.taccisum.domain.core.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-07-23
 */
@Slf4j
public abstract class SpringUtils {
    @Getter
    private static ApplicationContext context;

    public static void setContext(ApplicationContext context) {
        SpringUtils.context = context;
    }

    /**
     * <pre>
     * 为指定对象注入其依赖的 spring beans
     *
     * 当前仅支持：{@link Autowired} 字段注入
     * </pre>
     *
     * @param obj 目标对象
     */
    public static void inject(Object obj) {
        List<Field> autowiredFields = ClassUtils.listAllSuperClassesOf(obj.getClass(), true).stream()
                .flatMap(clazz -> Arrays.stream(clazz.getDeclaredFields()))
                .filter(field -> AnnotationUtils.findAnnotation(field, Autowired.class) != null)
                .collect(Collectors.toList());

        for (Field field : autowiredFields) {
            Object bean = context.getBean(field.getType());
            try {
                if (field.isAccessible()) {
                    field.set(obj, bean);
                } else {
                    field.setAccessible(true);
                    field.set(obj, bean);
                    field.setAccessible(false);
                }
            } catch (IllegalAccessException e) {
                log.warn("Can't access field \"{}\" of object {}, because of: {}", field.getName(), obj.toString(), e.getMessage());
            }
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
