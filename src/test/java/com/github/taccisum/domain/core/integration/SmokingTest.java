package com.github.taccisum.domain.core.integration;

import com.github.taccisum.domain.core.integration.domain.Foo;
import com.github.taccisum.domain.core.integration.domain.FooCreateEvent;
import com.github.taccisum.domain.core.integration.domain.FooRepository;
import com.github.taccisum.domain.core.spring.FactoryAspect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
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

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public FactoryAspect factoryAspect() {
            FactoryAspect bean = new FactoryAspect();
            bean.setLegacyInjectEnabled(true);
            return bean;
        }
    }

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
