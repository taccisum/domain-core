package com.github.taccisum.domain.core;

import java.io.Serializable;

/**
 * 关联关系对象
 *
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2023/11/2
 */
public interface LinkObject<ID extends Serializable, LID extends Serializable, RID extends Serializable> extends DataObject<ID> {
    LID getLeftId();

    void setLeftId(LID leftId);

    RID getRightId();

    void setRightId(RID rightId);
}
