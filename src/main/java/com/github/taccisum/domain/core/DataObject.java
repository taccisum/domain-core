package com.github.taccisum.domain.core;

import java.io.Serializable;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.5
 */
public interface DataObject<ID extends Serializable> {
    ID getId();

    void setId(ID id);
}
