package com.github.taccisum.domain.core.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author taccisum - liaojinfeng@baidu.ac.com
 * @since 2024/3/21
 */
@RunWith(SpringRunner.class)
public class SpringBasedDependenciesManagerTest {
    @Resource
    private ApplicationContext ctx;
    @Resource
    private Foo foo;

    @TestConfiguration
    public static class Config {
        @Bean
        public Foo foo() {
            return new Foo();
        }

        @Bean
        public Bar bar1() {
            return new Bar(1);
        }

        @Bean
        public Bar bar2() {
            return new Bar(2);
        }
    }

    @Test
    public void index() {
        SpringBasedDependenciesManager manager = new SpringBasedDependenciesManager(ctx);
        assertThat(manager.findOne(Foo.class)).isEqualTo(foo);
        assertThat(manager.tryFindOne(Foo.class)).isEqualTo(foo);
    }

    @Test(expected = NoSuchBeanDefinitionException.class)
    public void findOneNotExist() {
        SpringBasedDependenciesManager manager = new SpringBasedDependenciesManager(ctx);
        manager.findOne(String.class);
    }

    @Test(expected = NoUniqueBeanDefinitionException.class)
    public void findOneInMulti() {
        SpringBasedDependenciesManager manager = new SpringBasedDependenciesManager(ctx);
        manager.findOne(Bar.class);
    }

    @Test
    public void tryFindOneNotExist() {
        SpringBasedDependenciesManager manager = new SpringBasedDependenciesManager(ctx);
        assertThat(manager.tryFindOne(String.class)).isNull();
    }

    @Test
    public void tryFindOneInMulti() {
        SpringBasedDependenciesManager manager = new SpringBasedDependenciesManager(ctx);
        assertThat(manager.tryFindOne(Bar.class)).isNotNull();
        assertThat(manager.tryFindOne(Bar.class).getId()).isEqualTo(1);
    }

    public static class Foo {
    }

    @Data
    @AllArgsConstructor
    public static class Bar {
        private Integer id;
    }
}