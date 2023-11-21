package com.github.taccisum.domain.core.common.dict;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2023/11/21
 */
public interface Dict {
    /**
     * @param key namespace key
     * @return Get dict namespace even if it doesn't exist
     */
    Namespace ns(String key);

    interface Namespace {
        Item of(String code);

        Item of(int val);
    }

    interface Item {
        String code();

        int val();
    }
}
