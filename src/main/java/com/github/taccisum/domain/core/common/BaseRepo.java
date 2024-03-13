package com.github.taccisum.domain.core.common;

import com.github.taccisum.domain.core.DAO;
import com.github.taccisum.domain.core.DataObject;
import com.github.taccisum.domain.core.DomainException;
import com.github.taccisum.domain.core.Entity;
import com.github.taccisum.domain.core.data.CreatedAtProperty;
import com.github.taccisum.domain.core.exception.annotation.ErrorCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 0.5
 */
public abstract class BaseRepo<E extends Entity<?>, DO extends DataObject<?>> {
    /**
     * Data Access Object
     */
    protected DAO<DO> dao;

    public BaseRepo(DAO<DO> dao) {
        this.dao = dao;
    }

    public DAO<DO> getDao() {
        return this.dao;
    }

    /**
     * @param data Data object of entity
     * @return New created entity
     * @throws CreateException Fail to create and persist new entity
     */
    public E create(DO data) throws CreateException {
        if (data instanceof CreatedAtProperty) {
            ((CreatedAtProperty) data).setCreatedAt(new Date());
        }
        dao.insert(data);
        if (data.getId() == null) {
            throw new IllegalStateException("Must provide an identity for new entity.");
        }
        return this.toEntity(data);
    }

    /**
     * @param data  Data object of entity
     * @param clazz Expected class of new entity
     * @return New created entity
     * @throws CreateException Fail to create and persist new entity
     */
    public <T extends E> T create(DO data, Class<T> clazz) throws CreateException {
        E e = this.create(data);
        if (e.getClass().isAssignableFrom(clazz)) {
            return (T) e;
        }
        throw new CreateException(
                String.format("Real entity class %s doesn't match expected class %s.",
                        e.getClass(), clazz)
        );
    }

    /**
     * Find the entity by identity
     *
     * @param id Entity unique identity
     * @return Entity object or null(if data not found)
     */
    public Optional<E> getById(Serializable id) {
        DO data = this.dao.selectById(id);
        if (data == null) return Optional.empty();
        return Optional.of(this.toEntity(data));
    }

    /**
     * @param id    Unique identity of entity
     * @param clazz Expected class of entity
     * @return Entity object or null(if data not found or class not match)
     */
    public <T> Optional<T> getById(Serializable id, Class<T> clazz) {
        return this.getById(id)
                .map(it -> {
                    if (!it.getClass().isAssignableFrom(clazz)) return null;
                    return (T) it;
                });
    }

    public Optional<E> getBy(Function<DAO<? extends DO>, DO> queryOne) {
        if (queryOne == null) return Optional.empty();
        DO data = queryOne.apply(this.dao);
        if (data == null) return Optional.empty();
        return Optional.of(this.toEntity(data));
    }

    public List<E> queryBy(Function<DAO<? extends DO>, List<DO>> query) {
        List<DO> rows = query == null ? dao.selectAll() : query.apply(dao);
        return rows.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    /**
     * Delete entity by identity
     *
     * @param id Entity unique identity
     */
    public void deleteById(Serializable id) {
        this.dao.deleteById(id);
    }

    /**
     * Transfer data object to entity
     *
     * @param data Data object
     * @return The entity relates to the given data object
     */
    protected abstract E toEntity(DO data);

    @ErrorCode(value = "ENTITY.CREATE", description = "创建实体失败")
    public static class CreateException extends DomainException {
        public CreateException(String message) {
            super(message);
        }
    }
}
