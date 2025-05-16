package cn.edu.whut.sept.zuul.model;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private String email;
    private Date birthday;
    private String avatar;
    private float money;
}