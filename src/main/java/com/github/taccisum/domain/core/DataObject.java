package com.github.taccisum.domain.core;

import java.io.Serializable;

/**
 * Mark a class that implemented this interface a data object
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.5
 */
public interface DataObject<ID extends Serializable> {
    /**
     * @return Unique of relevant entity
     */
    ID getId();

    /**
     * @param id Unique of relevant entity
     */
    void setId(ID id);
}
