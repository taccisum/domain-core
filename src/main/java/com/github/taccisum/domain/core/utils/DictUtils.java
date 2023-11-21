package com.github.taccisum.domain.core.utils;

import com.github.taccisum.domain.core.common.dict.Dict;

/**
 * Data Dictionary Utilities
 *
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2023/11/21
 */
public abstract class DictUtils {
    private static Dict dict;

    public static void setDict(Dict dict) {
        DictUtils.dict = dict;
    }

    public static Dict.Namespace ns(String key) {
        return dict.ns(key);
    }
}
