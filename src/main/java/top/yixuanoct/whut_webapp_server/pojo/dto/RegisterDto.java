package top.yixuanoct.whut_webapp_server.pojo.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String name;
    private String password;
    private String email;
    private LocalDate birthday;
    private String avatar;
}
