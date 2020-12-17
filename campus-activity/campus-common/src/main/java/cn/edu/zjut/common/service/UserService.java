package cn.edu.zjut.common.service;

import cn.edu.zjut.common.domain.User;

public interface UserService {
    User LoginUser(String staff_id, String password);
    int createUser(User user);
}
