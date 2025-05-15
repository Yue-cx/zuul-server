package top.yixuanoct.whut_webapp_server.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.yixuanoct.whut_webapp_server.pojo.User;

@Mapper
@Repository
public interface UserRepository {
    void add(User user);

    void update(User user);

    User findByName(String name);
}
