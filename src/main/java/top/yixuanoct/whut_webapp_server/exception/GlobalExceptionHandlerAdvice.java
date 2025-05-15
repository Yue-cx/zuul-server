package top.yixuanoct.whut_webapp_server.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import top.yixuanoct.whut_webapp_server.pojo.Response;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(InvalidCredentialsException.class)
    public Response<Object> handleInvalidCredentialsException(InvalidCredentialsException e) {
        return new Response<>(401, "用户名或密码错误", null);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public Response<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new Response<>(409, "用户名已被注册", null);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return new Response<>(405, "请求方法错误：" + e.getMessage(), null);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Response<Object> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return new Response<>(415, "不支持的媒体类型: " + e.getMessage(), null);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new Response<>(400, "请求体解析失败: " + e.getMessage(), null);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Response<Object> handleNoResourceFoundException(NoResourceFoundException e) {
        return new Response<>(404, "请求的地址不存在", null);
    }

    @ExceptionHandler(Exception.class)
    public Response<Object> handleException(Exception e) {
        return new Response<>(500, "服务器内部错误", null);
    }
}
