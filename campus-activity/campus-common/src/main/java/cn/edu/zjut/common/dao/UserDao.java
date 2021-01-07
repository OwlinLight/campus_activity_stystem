package cn.edu.zjut.common.dao;

import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User loginUser(@Param("staffId") Long staffId, @Param("password") String password);

    int createUser(User user);

    User getUser(Long staffId);

    Long askIdByName(String userName);

    List<Activity> getUserActivity(@Param("staffId") Long staffId);
}
