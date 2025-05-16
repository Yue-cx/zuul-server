package cn.edu.whut.sept.zuul.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础业务异常父类
 */
@Data                          // 自动生成 getter/setter
@EqualsAndHashCode(callSuper = false)  // 避免父类(RuntimeException)的equals/hashCode影响
public abstract class BusinessException extends RuntimeException {
    /**
     * 这里其实更应该是错误码,如"USERNAME_EXISTS",而不是状态码
     * 状态码在GlobalHander中@ResponseStatus()注解实现
     */
    private final int errorCode;

    /**
     * @param errorCode 错误码(更好),而不状态码(多余)<br/>可惜只能是状态码(懒得改了)
     * @param message   错误信息
     */
    public BusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
