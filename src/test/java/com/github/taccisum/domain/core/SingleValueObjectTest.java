package com.github.taccisum.domain.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author taccisum - liaojinfeng@baidu.com
 * @since 2024/4/25
 */
public class SingleValueObjectTest {

    @Test
    public void testEquals() {
        assertThat(new Foo(1).equals(new Foo(1))).isTrue();
        assertThat(new Foo(1).equals(Foo.FOO_1)).isTrue();
        assertThat(new Foo(1).equals(new Foo(2))).isFalse();
    }

    @Test
    public void testInheritEquals() {
        assertThat(new Foo(1).equals(new SubFoo1(1))).isTrue();
        assertThat(new Foo(2).equals(new SubFoo1(1))).isFalse();
        assertThat(new SubFoo1(1).equals(new Foo(1))).isTrue();
        assertThat(new SubFoo1(1).equals(new Foo(2))).isFalse();
        assertThat(new SubFoo1(1).equals(new SubFoo2(1))).isFalse();
    }

    public static class Foo extends SingleValueObject<Integer> {
        public static final Foo FOO_1 = new Foo(1);

        public Foo(Integer val) {
            super(val);
        }
    }

    public static class SubFoo1 extends Foo {
        public SubFoo1(Integer val) {
            super(val);
        }
    }

    public static class SubFoo2 extends Foo {
        public SubFoo2(Integer val) {
            super(val);
        }
    }
}