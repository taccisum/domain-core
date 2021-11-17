package com.github.taccisum.domain.core.integration.domain;

import org.springframework.stereotype.Component;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
@Component
public class Factory implements com.github.taccisum.domain.core.Factory {
    public Foo newFoo(Long id) {
        return new Foo(id);
    }
}
