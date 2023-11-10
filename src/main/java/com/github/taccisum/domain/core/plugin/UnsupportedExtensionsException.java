package com.github.taccisum.domain.core.plugin;

import com.github.taccisum.domain.core.DomainException;
import com.github.taccisum.domain.core.exception.annotation.ErrorCode;

/**
 * 不支持的扩展异常
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.5
 */
@ErrorCode(value = "EXTENSIONS.UNSUPPORTED", description = "不支持的扩展")
public class UnsupportedExtensionsException extends DomainException {
    public UnsupportedExtensionsException(String message) {
        super(message);
    }
}
