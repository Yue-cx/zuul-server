package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.model.User;

public interface UserService {
    User register(User user);

    boolean login(String name, String password);

    boolean changePassword(String name, String oldPassword, String newPassword);

    User getUserById(Integer id);

    User getUserByName(String name);
}

