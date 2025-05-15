package top.yixuanoct.whut_webapp_server.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePwdDto {
    private String name;
    private String oldPassword;
    private String newPassword;
}
