package top.yixuanoct.whut_webapp_server.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import top.yixuanoct.whut_webapp_server.exception.InvalidCredentialsException;
import top.yixuanoct.whut_webapp_server.exception.UserAlreadyExistsException;
import top.yixuanoct.whut_webapp_server.pojo.User;
import top.yixuanoct.whut_webapp_server.pojo.dto.LoginDto;
import top.yixuanoct.whut_webapp_server.pojo.dto.RegisterDto;
import top.yixuanoct.whut_webapp_server.pojo.dto.UpdatePwdDto;
import top.yixuanoct.whut_webapp_server.repository.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User login(LoginDto loginDto) {
        User user = userRepository.findByName(loginDto.getName());
        if (user != null && passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public void register(RegisterDto registerDto) {
        User user = userRepository.findByName(registerDto.getName());
        if (user != null) {
            throw new UserAlreadyExistsException();
        } else {
            User newUser = new User();
            BeanUtils.copyProperties(registerDto, newUser);
            newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            userRepository.add(newUser);
        }
    }

    @Override
    public void updatePwd(UpdatePwdDto updatePwdDto) {
        User user = userRepository.findByName(updatePwdDto.getName());
        if (user != null && passwordEncoder.matches(updatePwdDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatePwdDto.getNewPassword()));
            userRepository.update(user);
        } else {
            throw new InvalidCredentialsException();
        }
    }
}
