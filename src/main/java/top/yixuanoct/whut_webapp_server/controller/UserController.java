package top.yixuanoct.whut_webapp_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.yixuanoct.whut_webapp_server.pojo.Response;
import top.yixuanoct.whut_webapp_server.pojo.User;
import top.yixuanoct.whut_webapp_server.pojo.dto.LoginDto;
import top.yixuanoct.whut_webapp_server.pojo.dto.RegisterDto;
import top.yixuanoct.whut_webapp_server.pojo.dto.UpdatePwdDto;
import top.yixuanoct.whut_webapp_server.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin; 

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Response<User> login(@RequestBody LoginDto loginDto) {
        User user = userService.login(loginDto);
        return Response.success(user);
    }

    @PostMapping("/register")
    public Response<Void> register(@RequestBody RegisterDto registerDto) {
        userService.register(registerDto);
        return Response.success(null);
    }

    @PostMapping("/updatePwd")
    public Response<Void> updatePwd(@RequestBody UpdatePwdDto UpdatePwdDto) {
        userService.updatePwd(UpdatePwdDto);
        return Response.success(null);
    }
}