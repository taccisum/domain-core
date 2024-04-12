package com.github.taccisum.domain.core.data;

/**
 * Mark the data object has 'status' property
 *
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2024/04/12
 */
public interface StatusProperty {
    /**
     * @return Entity status
     */
    Integer getStatus();

    /**
     * @param status Entity status
     */
    void setStatus(Integer status);
}
