package com.github.taccisum.ddd.core.utils;

import com.github.taccisum.ddd.core.utils.SpringUtilsTest.Bar;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-07-23
 */
@RunWith(SpringRunner.class)
@Import(Bar.class)
public class SpringUtilsTest {
    @Autowired
    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        SpringUtils.setContext(context);
    }

    @Test
    public void inject() {
        Foo foo = new Foo();
        SpringUtils.inject(foo);
        assertThat(foo).isNotNull();
        assertThat(foo.bar).isNotNull();
        assertThat(foo.bar1).isNull();
    }


    public static class Bar {
    }

    public static class Foo {
        @Autowired
        private Bar bar;
        private Bar bar1;
    }

}