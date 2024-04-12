package com.github.taccisum.domain.core.data;

/**
 * Mark the data object has 'kind' property
 *
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2023/11/16
 */
public interface KindProperty {
    /**
     * @return Kind of its relevant entity
     */
    Integer getKind();

    /**
     * @param kind Kind of its relevant entity
     */
    void setKind(Integer kind);
}
