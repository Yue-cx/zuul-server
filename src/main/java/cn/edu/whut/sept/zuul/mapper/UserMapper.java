package cn.edu.whut.sept.zuul.mapper;

import cn.edu.whut.sept.zuul.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insert(User user);
    User findByName(String name);
    User findById(Integer id);
    int updatePassword(@Param("id") Integer id,
                       @Param("oldPassword") String oldPassword,
                       @Param("newPassword") String newPassword);
}
