package com.github.taccisum.domain.core.integration.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
@Component
public class FooRepository {
    @Autowired
    private Factory factory;
    @Autowired
    private FooDAO dao;

    public Foo get(Long id) {
        FooDO data = dao.selectById(id);
        if (data != null) {
            return factory.newFoo(id);
        }
        return null;
    }
}
