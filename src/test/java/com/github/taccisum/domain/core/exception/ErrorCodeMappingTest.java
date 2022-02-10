package com.github.taccisum.domain.core.exception;

import com.github.taccisum.domain.core.DomainException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/2/10
 */
public class ErrorCodeMappingTest {
    private ErrorCodeMapping.Default mapping = new ErrorCodeMapping.Default();

    @Before
    public void setUp() throws Exception {
        mapping.link(Foo3Exception.class, "FOO3");
    }

    @Test
    public void index() {
        assertThat(mapping.map(new DomainException("")).value()).isEqualTo("DOMAIN_ERROR");
        assertThat(mapping.map(DataErrorException.class).value()).isEqualTo("DATA_ERROR");
        assertThat(mapping.map(DataNotFoundException.class).value()).isEqualTo("DATA_NOT_FOUND");
    }

    @Test
    public void findFromAnnotation() {
        ErrorCode code = mapping.map(new FooException());
        assertThat(code).isNotNull();
        assertThat(code.value()).isEqualTo("FOO");
    }

    @Test
    public void findFromSuperClass() {
        assertThat(mapping.map(new Foo1Exception()).value()).isEqualTo("FOO");
        assertThat(mapping.map(new Foo2Exception()).value()).isEqualTo("FOO2");
        assertThat(mapping.map(new Foo3Exception()).value()).isEqualTo("FOO3");
    }

    @com.github.taccisum.domain.core.exception.annotation.ErrorCode("FOO")
    public class FooException extends DomainException {
        public FooException() {
            super("foo");
        }
    }

    public class Foo1Exception extends FooException {
    }

    @com.github.taccisum.domain.core.exception.annotation.ErrorCode("FOO2")
    public class Foo2Exception extends FooException {
    }

    public class Foo3Exception extends FooException {
    }
}