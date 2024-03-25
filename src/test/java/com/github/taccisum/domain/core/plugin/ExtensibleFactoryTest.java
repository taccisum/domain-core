package com.github.taccisum.domain.core.plugin;

import com.github.taccisum.domain.core.DataObject;
import com.github.taccisum.domain.core.DependenciesManager;
import com.github.taccisum.domain.core.Entity;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pf4j.PluginManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2024/3/25
 */
@RunWith(SpringRunner.class)
public class ExtensibleFactoryTest {
    private ExtensibleFactory extensibleFactory;
    @MockBean
    private DependenciesManager dependenciesManager;
    @MockBean
    private PluginManager pluginManager;
    private ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        extensibleFactory = spy(new ExtensibleFactory(dependenciesManager));
        applicationContext = mock(ApplicationContext.class);
    }

    @Test
    public void index() {
        assertThat(extensibleFactory).isNotNull();
        assertThat(extensibleFactory.pluginManager).isNull();
        assertThat(extensibleFactory.applicationContext).isNull();
    }

    @Test
    public void create() {
        extensibleFactory.setApplicationContext(applicationContext);
        HashMap<String, FooFactory> factories = new HashMap<>();
        factories.put("a", new Foo.Factory());
        when(applicationContext.getBeansOfType(FooFactory.class)).thenReturn(factories);
        FooDO data = new FooDO();
        data.setId(1L);
        Foo foo = extensibleFactory.create(data.getId(), data, FooFactory.class);
        assertThat(foo).isNotNull();
        assertThat(foo.getClass()).isEqualTo(Foo.class);
        verify(extensibleFactory, times(1)).init(foo);
    }

    @Test
    public void createOnMatchingMultiFactories() {
        extensibleFactory.setApplicationContext(applicationContext);
        HashMap<String, FooFactory> factories = new HashMap<>();
        factories.put("a", new Foo.Factory());
        factories.put("b", new SubFoo.Factory());
        when(applicationContext.getBeansOfType(FooFactory.class)).thenReturn(factories);
        FooDO data = new FooDO();
        data.setId(1L);
        Foo foo = extensibleFactory.create(data.getId(), data, FooFactory.class);
        assertThat(foo).isNotNull();
        assertThat(foo.getClass()).isEqualTo(SubFoo.class);
    }

    @Test
    public void createByPlugin() {
        extensibleFactory.setPluginManager(pluginManager);
        List<FooFactory> factories = new ArrayList<>();
        factories.add(new Foo.Factory());
        when(pluginManager.getExtensions(FooFactory.class)).thenReturn(factories);
        FooDO data = new FooDO();
        data.setId(1L);
        Foo foo = extensibleFactory.create(data.getId(), data, FooFactory.class);
        assertThat(foo).isNotNull();
    }

    @Test(expected = ExtensibleFactory.NoSuitableFactoryFoundException.class)
    public void createWhenNoSuitableFactory() {
        FooDO data = new FooDO();
        data.setId(1L);
        extensibleFactory.create(data.getId(), data, FooFactory.class);
    }

    @Test
    public void createWhenSomeArgsNull() {
        assertThatThrownBy(() -> {
            extensibleFactory.create(null, null, FooFactory.class);
        }).isInstanceOf(NullPointerException.class).hasMessage("id");

        assertThatThrownBy(() -> {
            extensibleFactory.create(1L, null, null);
        }).isInstanceOf(NullPointerException.class).hasMessage("type");
    }

    @Data
    private static class FooDO implements DataObject<Long> {
        private Long id;
    }

    private static class Foo extends Entity.Base<Long> {
        public Foo(Long id) {
            super(id);
        }

        public static class Factory implements FooFactory {
            @Override
            public Foo create(Long id, FooDO criteria) {
                return new Foo(id);
            }

            @Override
            public boolean match(Long id, FooDO criteria) {
                return true;
            }
        }
    }

    private static class SubFoo extends Foo {
        public SubFoo(Long id) {
            super(id);
        }

        public static class Factory implements FooFactory {
            @Override
            public Foo create(Long id, FooDO criteria) {
                return new SubFoo(id);
            }

            @Override
            public boolean match(Long id, FooDO criteria) {
                return true;
            }

            @Override
            public int getOrder() {
                return 0;
            }
        }
    }

    private interface FooFactory extends EntityFactory<Long, Foo, FooDO> {
        @Override
        default int getOrder() {
            return Integer.MAX_VALUE;
        }
    }
}