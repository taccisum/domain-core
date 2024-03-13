package com.github.taccisum.domain.core.data;

import java.util.Date;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2024/3/11
 */
public interface CreatedAtProperty {
    /**
     * @return 创建时间
     */
    Date getCreatedAt();

    /**
     * @param createdAt 创建时间
     */
    void setCreatedAt(Date createdAt);
}
