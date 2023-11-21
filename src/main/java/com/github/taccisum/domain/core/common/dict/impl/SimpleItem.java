package com.github.taccisum.domain.core.common.dict.impl;

import com.github.taccisum.domain.core.common.dict.Dict;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2023/11/21
 */
public class SimpleItem implements Dict.Item {
    private String code;
    private int val;

    public SimpleItem(String code, int val) {
        this.code = code;
        this.val = val;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public int val() {
        return this.val;
    }
}
