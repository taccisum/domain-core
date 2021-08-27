package com.github.taccisum.ddd.core.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-08-27
 */
public abstract class ClassUtils {
    /**
     * 获取指定类的所有父类
     *
     * @param clazz       目标类
     * @param includeSelf 返回结果中是否包含目标类自身
     */
    public static List<Class> listAllSuperClassesOf(Class clazz, boolean includeSelf) {
        List<Class> classes = new ArrayList<>();
        Class cls = clazz;
        if (includeSelf) {
            classes.add(cls);
        }
        while (cls.getSuperclass() != null) {
            cls = cls.getSuperclass();
            classes.add(cls);
        }
        return classes;
    }
}
