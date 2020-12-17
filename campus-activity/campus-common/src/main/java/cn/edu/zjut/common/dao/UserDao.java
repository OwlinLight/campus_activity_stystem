package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User LoginUser(@Param("staff_id") String staff_id, @Param("password") String password);
    int createUser(User user);
}
