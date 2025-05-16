package cn.edu.whut.sept.zuul.exception;

import cn.edu.whut.sept.zuul.exception.BusinessException;

public class DuplicateUsernameException extends BusinessException{
    public DuplicateUsernameException() {
        super(409, "用户名已存在");
    }
}
