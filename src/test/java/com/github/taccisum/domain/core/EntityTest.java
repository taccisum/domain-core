package com.github.taccisum.domain.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
public class EntityTest {
    @Test
    public void index() {
        assertThat(new Foo(1L).id()).isEqualTo(1L);
    }

    public class Foo extends Entity.Base<Long> {
        public Foo(Long id) {
            super(id);
        }
    }
}