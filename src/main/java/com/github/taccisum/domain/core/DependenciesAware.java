package com.github.taccisum.domain.core;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 0.5
 */
public interface DependenciesAware {
    /**
     * 为实体注入依赖
     *
     * @param manager 依赖管理者
     */
    void inject(DependenciesManager manager);
}
