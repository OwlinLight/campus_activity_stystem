package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User loginUser(@Param("staffId") Long staffId, @Param("password") String password);

    int createUser(User user);

    User getUser(Long staffId);

}
