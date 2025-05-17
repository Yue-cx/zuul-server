package cn.edu.whut.sept.zuul.exception;

import cn.edu.whut.sept.zuul.exception.BusinessException;

public class NotFoundException extends BusinessException{
    public NotFoundException(String errMsg) {
        super(404, errMsg);
    }
}
