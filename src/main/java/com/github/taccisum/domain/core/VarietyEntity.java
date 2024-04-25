package com.github.taccisum.domain.core;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author taccisum - liaojinfeng@baidu.com
 * @since 2024/4/25
 */
public abstract class VarietyEntity<ID extends Serializable, DO extends DataObject<ID>> extends Entity.Base<ID> {
    public VarietyEntity(ID id) {
        super(id);
    }

    /**
     * @return Data of this variety entity
     */
    public DO getData() {
        return this.getDao().selectById(this.id());
    }

    /**
     * @return DAO for accessing data object of this entity class
     */
    protected abstract DAO<DO> getDao();

    public abstract DataObject.Wrapper<DO> newDataWrapper();

    public void save(DataObject.Wrapper<DO> dw) {
        this.save(dw.getData());
    }

    protected void save(DO o) {
        if (o.getId() == null) {
            o.setId(this.id());
        }
        if (!Objects.equals(o.getId(), this.id())) {
            throw new IllegalArgumentException(
                    String.format("Data(%s) in wrapper does not belong to entity %s.", getData().getId(), this.id())
            );
        }
        this.getDao().updateById(o);
    }
}
