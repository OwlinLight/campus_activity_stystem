package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.dao.UserDao;
import cn.edu.zjut.common.domain.User;
import cn.edu.zjut.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;

    @Override
    public User loginUser(Long staffId, String password) {
        return userdao.loginUser(staffId, password);
    }

    @Override
    public int createUser(User user) {
        return userdao.createUser(user);
    }

    @Override
    public User getUser(Long staffId) {
        return userdao.getUser(staffId);
    }
}
