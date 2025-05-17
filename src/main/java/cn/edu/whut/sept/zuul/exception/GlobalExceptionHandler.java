package cn.edu.whut.sept.zuul.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 统一错误响应格式
    @Data
    @AllArgsConstructor
    private static class ErrorResponse {
        private int code;
        private String message;
        private long timestamp;
    }

    // 处理用户名重复异常
    @ExceptionHandler(DuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    public ErrorResponse handleDuplicateUsername(DuplicateUsernameException ex) {
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage(), System.currentTimeMillis());
    }

    // 处理资源不存在异常
    @ExceptionHandler(cn.edu.whut.sept.zuul.exception.UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorResponse handleResourceNotFound(cn.edu.whut.sept.zuul.exception.UserNotFoundException ex) {
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage(), System.currentTimeMillis());
    }

    @ExceptionHandler(ContactNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorResponse handleResourceNotFound(ContactNotFoundException ex) {
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage(), System.currentTimeMillis());
    }

    @ExceptionHandler(NotFoundException.class) //一般找不到异常
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    public ErrorResponse handleResourceNotFound(NotFoundException ex) {
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage(), System.currentTimeMillis());
    }

    // 处理参数验证异常（如 @Valid 失败）
    @ExceptionHandler(cn.edu.whut.sept.zuul.exception.ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorResponse handleValidation(cn.edu.whut.sept.zuul.exception.ValidationException ex) {
        return new ErrorResponse(400, "参数错误: " +ex.getMessage(), System.currentTimeMillis());
    }

    // 处理其他未捕获异常（兜底）
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorResponse handleUnknownException(Exception ex) {
        //log.error("系统异常: ", ex); // 记录日志
        return new ErrorResponse(500, "系统繁忙，请稍后重试", System.currentTimeMillis());
    }
}