package com.github.taccisum.domain.core.common.dict.impl;

import com.github.taccisum.domain.core.common.dict.Dict;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple implementation of {@link Dict}
 *
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2023/11/21
 */
@Slf4j
public class SimpleDict implements Dict {
    private final Map<String, SimpleNamespace> namespaces = new ConcurrentHashMap<>();
    private final Map<String, Item> items = new ConcurrentHashMap<>();

    public SimpleDict(List<DataInitializer> initializers) {
        if (initializers != null) {
            for (DataInitializer initializer : initializers) {
                initializer.init(this);
            }
        }
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void addItem(String ns, String code, int val) {
        String key = String.format("%s.%s", ns, code);
        if (items.containsKey(key)) {
            log.warn("Dict item '{}' exists! Repeat adding with val '{}' will be ignored!", key, val);
            return;
        }
        items.putIfAbsent(key, new SimpleItem(code, val));
    }

    @Override
    public SimpleNamespace ns(String key) {
        if (!namespaces.containsKey(key)) {
            namespaces.putIfAbsent(key, new SimpleNamespace(key, this));
        }
        return namespaces.get(key);
    }

    public interface DataInitializer {
        void init(SimpleDict dict);
    }
}
