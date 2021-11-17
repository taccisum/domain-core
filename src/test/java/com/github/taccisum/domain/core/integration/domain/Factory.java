package com.github.taccisum.domain.core.integration.domain;

import com.github.taccisum.domain.core.utils.SpringUtils;
import org.springframework.stereotype.Component;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
@Component
public class Factory {
    public Foo newFoo(Long id) {
        Foo foo = new Foo(id);
        SpringUtils.inject(foo);
        return foo;
    }
}
