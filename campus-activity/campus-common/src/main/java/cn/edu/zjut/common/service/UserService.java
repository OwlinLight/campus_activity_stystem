package cn.edu.zjut.common.service;

import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.User;

import java.util.List;

public interface UserService {
    User loginUser(Long staffId, String password);

    int createUser(User user);

    /**
     * 根据staffId查找用户
     */
    User getUser(Long staffId);

    Long askIdByName(String userName);

    List<Activity> getUserActivity(Long staffId);
}
