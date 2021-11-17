package com.github.taccisum.domain.core.integration.domain;

import com.github.taccisum.domain.core.Entity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
public class Foo extends Entity.Base<Long> {
    @Autowired
    private FooDAO dao;

    public Foo(Long id) {
        super(id);
    }

    public FooDO data() {
        return this.dao.selectById(this.id());
    }
}
