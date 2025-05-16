package cn.edu.whut.sept.zuul.service;

import cn.edu.whut.sept.zuul.mapper.UserMapper;
import cn.edu.whut.sept.zuul.model.User;
import cn.edu.whut.sept.zuul.service.UserService;
import cn.edu.whut.sept.zuul.exception.ValidationException;
import cn.edu.whut.sept.zuul.exception.UserNotFoundException;
import cn.edu.whut.sept.zuul.exception.DuplicateUsernameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User register(User user) {
        // 检查用户名是否已存在
        User existingUser = userMapper.findByName(user.getName());
        if (existingUser != null) {
            throw new DuplicateUsernameException();
        }

        // 对密码进行MD5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        // 插入用户数据
        userMapper.insert(user);
        return user;
    }

    @Override
    public boolean login(String name, String password) {
        User user = userMapper.findByName(name);
        if (user == null) {
            throw new UserNotFoundException();//用户不存在
        }

        // 验证密码
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!user.getPassword().equals(encryptedPassword)) {
            throw new ValidationException("密码错误");//密码错误
        }

        return true;
    }

    @Override
    public boolean changePassword(String name, String oldPassword, String newPassword) {
        // 加密旧密码和新密码
        String encryptedOldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        String encryptedNewPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());

        Integer userId=getUserByName(name).getId();
        int affectedRows = userMapper.updatePassword(userId, encryptedOldPassword, encryptedNewPassword);
        if(affectedRows > 0){
            return true;
        }
        else{
            throw new ValidationException("账户名与旧密码不匹配! ");
        }
    }

    @Override
    public User getUserById(Integer id) {
        User user=userMapper.findById(id);
        if(user==null){
            throw new UserNotFoundException();
        }else {
            return user;
        }
    }

    @Override
    public User getUserByName(String name){
        User user=userMapper.findByName(name);
        if(user==null){
            throw new UserNotFoundException();
        }else{
            return user;
        }
    }
}
