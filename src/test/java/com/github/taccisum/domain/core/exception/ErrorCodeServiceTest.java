package com.github.taccisum.domain.core.exception;

import com.github.taccisum.domain.core.DomainException;
import com.github.taccisum.domain.core.exception.annotation.ErrorCode;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/2/10
 */
public class ErrorCodeServiceTest {
    private ErrorCodeService service;

    @Before
    public void setUp() throws Exception {
        service = new ErrorCodeService("test");
        service.setErrorCodeMapping(new ErrorCodeMapping.Default());
    }

    @Test
    public void index() {
        assertThat(service.getErrorCode(new FooException())).isEqualTo("test.FOO");
    }

    @Test
    public void inherited() {
        assertThat(service.getErrorCode(new SubFooException())).isEqualTo("test.FOO.SUB_FOO");
        assertThat(service.getErrorCode(new SubSubFooException())).isEqualTo("test.FOO.SUB_FOO.SUB_SUB_FOO");
        assertThat(service.getErrorCode(new SubSubFoo1Exception())).isEqualTo("test.SUB_SUB_FOO1");
    }

    @ErrorCode(value = "FOO")
    public class FooException extends DomainException {
        public FooException() {
            super("");
        }
    }

    @ErrorCode(value = "SUB_FOO", inherited = true)
    public class SubFooException extends FooException {
    }

    @ErrorCode(value = "SUB_SUB_FOO", inherited = true)
    public class SubSubFooException extends SubFooException {
    }

    @ErrorCode(value = "SUB_SUB_FOO1", inherited = false)
    public class SubSubFoo1Exception extends SubFooException {
    }
}