package com.github.taccisum.domain.core.common.dict.impl;

import com.github.taccisum.domain.core.common.dict.Dict;

import java.util.Map;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2023/11/21
 */
public class SimpleNamespace implements Dict.Namespace {
    private String key;
    private SimpleDict dict;

    public SimpleNamespace(String key, SimpleDict dict) {
        this.key = key;
        this.dict = dict;
    }

    public void add(String code, int val) {
        dict.addItem(this.key, code, val);
    }

    @Override
    public Dict.Item of(String code) {
        Map<String, Dict.Item> items = dict.getItems();
        if (items.containsKey(this.getFullKey(code))) {
            return items.get(this.getFullKey(code));
        }
        return null;
    }

    @Override
    public Dict.Item of(int val) {
        // TODO:: optimize
        for (String key : dict.getItems().keySet()) {
            if (key.startsWith(String.format("%s.", this.key))) {
                Dict.Item item = dict.getItems().get(key);
                if (item.val() == val) {
                    return item;
                }
            }
        }

        return null;
    }

    private String getFullKey(String code) {
        return String.format("%s.%s", this.key, code);
    }
}
