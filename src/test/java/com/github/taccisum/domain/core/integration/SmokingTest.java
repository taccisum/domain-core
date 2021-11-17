package com.github.taccisum.domain.core.integration;

import com.github.taccisum.domain.core.integration.domain.Foo;
import com.github.taccisum.domain.core.integration.domain.FooCreateEvent;
import com.github.taccisum.domain.core.integration.domain.FooRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 集成测试 - 冒烟
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2021-11-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokingTest {
    @Autowired
    private FooRepository fooRepository;

    @Test
    public void index() {
        Foo foo = fooRepository.get(1L);
        assertThat(foo).isNotNull();
        assertThat(foo.id()).isEqualTo(1L);
        assertThat(foo.data().getId()).isEqualTo(1L);

        FooCreateEvent event = new FooCreateEvent();
        foo.publish(event);
        assertThat(event.getPublisher()).isEqualTo(foo);
    }
}
