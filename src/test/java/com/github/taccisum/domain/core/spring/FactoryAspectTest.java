package com.github.taccisum.domain.core.spring;

import com.github.taccisum.domain.core.EventBus;
import com.github.taccisum.domain.core.EventBusAware;
import com.github.taccisum.domain.core.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2024/3/25
 */
@Import({FactoryAspect.class, FactoryAspectTest.FooFactory.class})
@EnableAspectJAutoProxy
@RunWith(SpringRunner.class)
public class FactoryAspectTest {
    @Resource
    private FooFactory factory;
    @MockBean
    private EventBus eventBus;

    @Test
    public void setEventBus() {
        Foo foo = factory.create();
        assertThat(foo).isNotNull();
        assertThat(foo.eventBus).isNotNull();
        assertThat(foo.eventBus).isEqualTo(eventBus);
    }

    @Test
    public void setEventBusWhenMethodsReturnNull() {
        Foo foo = factory.createNull();
        assertThat(foo).isNull();
    }

    public static class FooFactory implements Factory {
        public Foo create() {
            return new Foo();
        }

        public Foo createNull() {
            return null;
        }
    }

    public static class Foo implements EventBusAware {
        private EventBus eventBus;

        @Override
        public void setEventBus(EventBus eventBus) {
            this.eventBus = eventBus;
        }
    }
}