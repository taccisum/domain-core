package com.github.taccisum.domain.core.data;

/**
 * Mark the data object has 'code' property
 *
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2024/04/12
 */
public interface CodeProperty {
    /**
     * @return Unique code of this entity
     */
    Integer getCode();

    /**
     * @param code Unique code of this entity
     */
    void setCode(Integer code);
}
