package cn.edu.whut.sept.zuul.exception;
import cn.edu.whut.sept.zuul.exception.BusinessException;

public class ValidationException extends BusinessException{
    public ValidationException(String message) {
        super(400, message);
    }
}
