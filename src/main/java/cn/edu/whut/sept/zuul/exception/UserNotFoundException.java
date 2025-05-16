package cn.edu.whut.sept.zuul.exception;

import cn.edu.whut.sept.zuul.exception.BusinessException;

public class UserNotFoundException extends BusinessException{
        public UserNotFoundException() {
            super(404, "该用户不存在");
        }
}
