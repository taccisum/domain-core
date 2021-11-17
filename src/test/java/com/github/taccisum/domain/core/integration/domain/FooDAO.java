package com.github.taccisum.domain.core.integration.domain;

import org.springframework.stereotype.Component;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
@Component
public class FooDAO {
    public FooDO selectById(Long id) {
        FooDO data = new FooDO();
        data.setId(id);
        return data;
    }
}
