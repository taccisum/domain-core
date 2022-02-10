package com.github.taccisum.domain.core.exception;

import com.github.taccisum.domain.core.DomainException;
import org.springframework.util.StringUtils;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.4
 */
public class ErrorCodeService {
    /**
     * 错误源，所有错误码值均会带上此前缀以方便溯源
     */
    private String errorSource;
    private ErrorCodeMapping errorCodeMapping;

    public ErrorCodeService() {
    }

    public ErrorCodeService(String errorSource) {
        this.errorSource = errorSource;
    }

    public void setErrorCodeMapping(ErrorCodeMapping errorCodeMapping) {
        this.errorCodeMapping = errorCodeMapping;
    }

    /**
     * 获取错误码
     */
    public String getErrorCode(DomainException e) {
        return this.getErrorCode(e.getClass());
    }

    public String getErrorCode(Class<? extends DomainException> clazz) {
        return this.getErrorCode(clazz, true);
    }

    private String getErrorCode(Class<? extends DomainException> clazz, boolean appendSource) {
        ErrorCode code = this.errorCodeMapping.map(clazz);

        if (code == null) {
            return "UNKNOWN_DOMAIN_ERROR";
        }

        String codeStr = code.value();

        if (code.inherited()) {
            Class<?> pClazz = clazz.getSuperclass();
            if (DomainException.class.isAssignableFrom(pClazz)) {
                // find parent exception's error code
                String pCode = this.getErrorCode((Class<? extends DomainException>) pClazz, false);

                // TODO:: optimize perf
                if (pCode != null && pCode.length() > 0) {
                    codeStr = String.format("%s.%s", pCode, codeStr);
                }
            }
        }

        if (appendSource) {
            if (StringUtils.isEmpty(errorSource)) {
                return codeStr;
            } else {
                return String.format("%s.%s", errorSource, codeStr);
            }
        }
        return codeStr;
    }
}
