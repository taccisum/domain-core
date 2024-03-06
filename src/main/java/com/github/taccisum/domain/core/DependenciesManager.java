package com.github.taccisum.domain.core;

/**
 * 依赖管理者
 *
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 0.5
 */
public interface DependenciesManager {
    <T> T findOne(Class<T> clazz);

    /**
     * Try best to find one available bean. Or null if not exist
     *
     * @since 0.6
     */
    <T> T tryFindOne(Class<T> clazz);
}
