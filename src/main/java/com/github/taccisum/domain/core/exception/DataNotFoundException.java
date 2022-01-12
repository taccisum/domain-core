package com.github.taccisum.domain.core.exception;

import com.github.taccisum.domain.core.DomainException;

/**
 * 数据不存在异常
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.3
 */
public class DataNotFoundException extends DomainException {
    public DataNotFoundException(String key, Object id) {
        super("%s[%s]不存在", key, id);
    }
}
