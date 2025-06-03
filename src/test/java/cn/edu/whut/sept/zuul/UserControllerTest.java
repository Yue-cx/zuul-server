package cn.edu.whut.sept.zuul;

import cn.edu.whut.sept.zuul.exception.DuplicateUsernameException;
import cn.edu.whut.sept.zuul.exception.UserNotFoundException;
import cn.edu.whut.sept.zuul.exception.ValidationException;
import cn.edu.whut.sept.zuul.model.User;
import cn.edu.whut.sept.zuul.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    // 创建测试用户对象（使用set方法初始化）
    private User createTestUser() {
        User user = new User();
        user.setId(1);
        user.setName("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setBirthday(new Date());
        user.setAvatar("default.jpg");
        user.setMoney(100.0f);
        return user;
    }

    // 创建简化版登录请求用户对象
    private User createLoginRequest() {
        User user = new User();
        user.setName("testUser");
        user.setPassword("password123");
        return user;
    }

    @Test
    void register_ShouldReturnUser_WhenSuccess() throws Exception {
        User testUser = createTestUser();
        Mockito.when(userService.register(Mockito.any(User.class))).thenReturn(testUser);

        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("testUser"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void register_ShouldReturnConflict_WhenUsernameExists() throws Exception {
        Mockito.when(userService.register(Mockito.any(User.class)))
                .thenThrow(new DuplicateUsernameException());

        mockMvc.perform(post("/api/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createTestUser())))
                .andExpect(status().isConflict());
    }

    @Test
    void login_ShouldReturnUser_WhenCredentialsCorrect() throws Exception {
        User testUser = createTestUser();
        Mockito.when(userService.login("testUser", "password123")).thenReturn(true);
        Mockito.when(userService.getUserByName("testUser")).thenReturn(testUser);

        mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createLoginRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("testUser"))
                .andExpect(jsonPath("$.avatar").value("default.jpg"));
    }

    @Test
    void login_ShouldReturnUnauthorized_WhenCredentialsInvalid() throws Exception {
        Mockito.when(userService.login(Mockito.anyString(), Mockito.anyString()))
                .thenThrow(new ValidationException("密码错误"));

        User loginRequest = createLoginRequest();
        loginRequest.setPassword("wrongPassword");

        mockMvc.perform(post("/api/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void changePassword_ShouldReturnTrue_WhenSuccess() throws Exception {
        Mockito.when(userService.changePassword("testUser", "oldPwd", "newPwd"))
                .thenReturn(true);

        mockMvc.perform(post("/api/user/updatePwd")
                .param("name", "testUser")
                .param("oldPassword", "oldPwd")
                .param("newPassword", "newPwd"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void changePassword_ShouldReturnBadRequest_WhenOldPasswordWrong() throws Exception {
        Mockito.when(userService.changePassword(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenThrow(new ValidationException("旧密码错误"));

        mockMvc.perform(post("/api/user/updatePwd")
                .param("name", "testUser")
                .param("oldPassword", "wrongPwd")
                .param("newPassword", "newPwd"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUser_ShouldReturnUser_WhenUserExists() throws Exception {
        User testUser = createTestUser();
        Mockito.when(userService.getUserById(1)).thenReturn(testUser);

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("testUser"))
                .andExpect(jsonPath("$.money").value(100.0));
    }

    @Test
    void getUser_ShouldReturnNotFound_WhenUserNotExists() throws Exception {
        Mockito.when(userService.getUserById(999))
                .thenThrow(new UserNotFoundException());

        mockMvc.perform(get("/api/user/999"))
                .andExpect(status().isNotFound());
    }
}