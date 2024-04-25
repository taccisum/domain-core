package com.github.taccisum.domain.core;

import java.util.Objects;

/**
 * The value object with only one value
 *
 * @author taccisum - liaojinfeng@baidu.com
 * @since 2024/4/25
 */
public abstract class SingleValueObject<T> implements ValueObject {
    public final T val;

    public SingleValueObject(T val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!this.getClass().isAssignableFrom(o.getClass())
                && !o.getClass().isAssignableFrom(this.getClass())
        ) return false;       // For inherit scene

        SingleValueObject<?> that = (SingleValueObject<?>) o;

        return Objects.equals(val, that.val);
    }

    @Override
    public int hashCode() {
        return val != null ? val.hashCode() : 0;
    }
}
