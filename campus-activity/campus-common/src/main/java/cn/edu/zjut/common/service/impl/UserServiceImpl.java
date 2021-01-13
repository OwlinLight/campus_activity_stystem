package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.dao.UserDao;
import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.User;
import cn.edu.zjut.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int insertUser(User user) {
        return userdao.insertUser(user);
    }

    @Override
    public int deleteUser(int id) { return userdao.deleteUser(id); }

    @Override
    public int updateUser(int id,User user) {
        user.setId(id);
        return userdao.updateUser(user);
    }

    @Override
    public int changepassword(int id,User user) {
        user.setId(id);
        return userdao.changepassword(user);
    }

    @Override
    public List<User> ListAllUser() { return userdao.ListAllUser(); }

    @Override
    public User getUser(Long staffId) {
        return userdao.getUser(staffId);
    }

    @Override
    public Long askIdByName(String userName) {

//        return userdao.askIdByName("徐伟峰") == null ? (long)-1 : (long)1;
//        return (long) 6;
        return userdao.askIdByName(userName);

    }

    @Override
    public List<Activity> getUserActivity(Long staffId) {
        return userdao.getUserActivity(staffId);
    }

    public static void main(String[] argv) {
        UserServiceImpl ustest = new UserServiceImpl();
//        System.out.println(ustest.askIdByName("徐伟峰"));
    }
}
