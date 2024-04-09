package com.github.taccisum.domain.core;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T> 此 DAO 管理的数据对象类型
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.5
 */
public interface DAO<T extends DataObject<?>> {
    /**
     * 新增一条数据
     *
     * @param data 数据
     * @param <ID> id 类型
     * @return 唯一 id
     */
    <ID extends Serializable> ID insert(T data);

    /**
     * 根据 ID 删除一条数据
     *
     * @param id 目标 id
     */
    void deleteById(Serializable id);

    /**
     * @param id 目标 id
     * @return 根据 id 查询的数据
     */
    T selectById(Serializable id);

    /**
     * @return 所有数据
     */
    List<T> selectAll();

    /**
     * 根据 id 更新数据
     *
     * @param data 数据对象
     */
    void updateById(T data);

    /**
     * @return 新建一个空的 Data Object 实例
     */
    T newEmptyDataObject();

    interface Readonly<T extends DataObject<?>> extends DAO<T> {
        @Override
        default <ID extends Serializable> ID insert(T data) {
            throw new UnsupportedOperationException();
        }

        @Override
        default void deleteById(Serializable id) {
            throw new UnsupportedOperationException();
        }

        @Override
        default void updateById(T data) {
            throw new UnsupportedOperationException();
        }
    }
}
