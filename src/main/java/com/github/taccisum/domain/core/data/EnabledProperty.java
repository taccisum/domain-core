package com.github.taccisum.domain.core.data;

/**
 * Mark the data object has 'enabled' property
 *
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2024/07/30
 */
public interface EnabledProperty {
    /**
     * @return Entity enabled indicator
     */
    Boolean getEnabled();

    /**
     * @param enabled Entity enabled indicator
     */
    void setEnabled(Boolean enabled);
}
