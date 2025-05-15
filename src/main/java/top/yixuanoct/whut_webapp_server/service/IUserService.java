package top.yixuanoct.whut_webapp_server.service;

import org.springframework.stereotype.Service;

import top.yixuanoct.whut_webapp_server.pojo.User;
import top.yixuanoct.whut_webapp_server.pojo.dto.LoginDto;
import top.yixuanoct.whut_webapp_server.pojo.dto.RegisterDto;
import top.yixuanoct.whut_webapp_server.pojo.dto.UpdatePwdDto;

@Service
public interface IUserService {
    User login(LoginDto loginDto);

    void register(RegisterDto registerDto);

    void updatePwd(UpdatePwdDto updatePwdDto);
}
