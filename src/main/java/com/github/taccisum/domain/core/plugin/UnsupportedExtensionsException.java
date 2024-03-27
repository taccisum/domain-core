package com.github.taccisum.domain.core.plugin;

/**
 * 不支持的扩展异常
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.5
 */
public class UnsupportedExtensionsException extends RuntimeException {
    public UnsupportedExtensionsException(String message) {
        super(message);
    }
}
