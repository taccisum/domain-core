package com.github.taccisum.domain.core.exception;

import com.github.taccisum.domain.core.DomainException;

/**
 * 数据错误异常
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.3
 */
public class DataErrorException extends DomainException {
    public DataErrorException(String key, Object id, String prompt) {
        super("%s[%s] 数据有误：%s", key, id, prompt);
    }

    public DataErrorException(String key, Object id, String field, String errVal, String expectVal) {
        super("%s[%s] 字段 %s 值有误，错误值：%s，期望值：%s", key, id, field, errVal, expectVal);
    }
}
