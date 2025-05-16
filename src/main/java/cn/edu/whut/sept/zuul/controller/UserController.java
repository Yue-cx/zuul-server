package cn.edu.whut.sept.zuul.controller;

import cn.edu.whut.sept.zuul.model.User;
import cn.edu.whut.sept.zuul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param user 得用json的requestbody,一般的表单格式不再适用
     *             并且http头中需包含Content-type: application/json
     * @return json格式的user数据
     */
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // 用户登录
    @PostMapping("/login")
    public User login(@RequestParam String name, @RequestParam String password) {
        if (userService.login(name, password)) {
            return userService.getUserByName(name);
        }
        else
            return null;
    }

    // 修改密码
    @PostMapping("/change-password")
    public boolean changePassword(@RequestParam String name,
                                  @RequestParam String oldPassword,
                                  @RequestParam String newPassword) {
        return userService.changePassword(name, oldPassword, newPassword);
    }

    // 获取用户信息
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }
}