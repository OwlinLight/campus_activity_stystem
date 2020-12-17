package cn.edu.zjut.common.controller;


import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.domain.User;
import cn.edu.zjut.common.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // 允许所有ip跨域
@Controller
public class UserController {
    @Autowired
    private UserService userserver;

    @RequestMapping(value = "/user/LoginUser/{staff_id}/{password}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<User> LoginUser(@PathVariable("staff_id") String staff_id, @PathVariable("password") String password) {
        CommonResult commonResult;
        User user = (User) userserver.LoginUser(staff_id, password);
        if (user == null) {
            commonResult = CommonResult.failed("登录失败");

        } else {
            commonResult = CommonResult.success(user);
        }
        return commonResult;
    }

    @RequestMapping(value = "/user/createUser", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createUser(@RequestBody User user) {
        CommonResult commonResult;
        int count = userserver.createUser(user);
        if (count == 1)
        {
            commonResult = CommonResult.success(user);
        }else
        {
            commonResult = CommonResult.failed("注册失败");
        }
        return commonResult;
    }


}
