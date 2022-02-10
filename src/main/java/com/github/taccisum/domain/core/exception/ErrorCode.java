package com.github.taccisum.domain.core.exception;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 0.4
 */
public interface ErrorCode {
    /**
     * code 值
     */
    String value();

    /**
     * code 描述
     */
    String description();

    /**
     * 是否继承父类关联的 {@link ErrorCode} 内容
     * <p>
     * 示例：当前类错误码为 BAR，父类为 FOO，则当前类最终输出错误码变为 FOO.BAR
     */
    boolean inherited();

    @Data
    @Accessors(fluent = true)
    class Default implements ErrorCode {
        private String value;
        private String desc;
        private boolean inherited = false;

        public Default(String code) {
            this.value = code;
        }

        public Default(String code, String desc) {
            this.value = code;
            this.desc = desc;
        }

        @Override
        public String description() {
            return desc == null ? "" : desc;
        }
    }
}
