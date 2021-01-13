package cn.edu.zjut.common.controller;


import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.domain.Activity;
import cn.edu.zjut.common.domain.Showac;
import cn.edu.zjut.common.domain.User;
import cn.edu.zjut.common.service.ParticipationService;
import cn.edu.zjut.common.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import cn.edu.zjut.common.utils.Sha256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@Api(
        tags = {"UserController"},
        description = "用户管理"
)
@CrossOrigin // 允许所有ip跨域
@Controller
public class UserController {
    @Autowired
    private UserService userservice;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @ApiOperation("用户登录")
    @RequestMapping(value = "/activity/LoginUser", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<User> loginUser(@RequestParam(value = "userName") Long staff_id, @RequestParam(value = "password") String password) {
        CommonResult commonResult;
        //把password通过加密后再进行登录
        password = Sha256.getSHA256StrJava(password);
        User user = (User) userservice.loginUser(staff_id, password);
        if (user == null) {
            commonResult = CommonResult.failed("登录失败");
        } else {
            commonResult = CommonResult.success(user);
        }
        return commonResult;
    }

    @ApiOperation("用户注册")
    @RequestMapping(value = "/activity/createUser", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createUser(@RequestBody User user) {
        CommonResult commonResult;
        //把user的password通过加密后进行注册
        String Pass = user.getPassword();
        Pass = Sha256.getSHA256StrJava(Pass);
        user.setPassword(Pass);
        int count = userservice.createUser(user);
        if (count == 1) {
            commonResult = CommonResult.success(user);
        } else {
            commonResult = CommonResult.failed("注册失败");
        }
        return commonResult;
    }

    @ApiOperation("用户添加")
    @RequestMapping(value = "/activity/insertUser", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insertUser(@RequestBody User user) {
        CommonResult commonResult;
        //把user的password通过加密后进行添加
        String StaffId=user.getStaffId().toString();
        String Pass = StaffId.substring(StaffId.length()-6);
        Pass = Sha256.getSHA256StrJava(Pass);
        user.setPassword(Pass);
        int count = userservice.insertUser(user);
        if (count == 1) {
            commonResult = CommonResult.success(user);
        } else {
            commonResult = CommonResult.failed("添加失败");
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的用户")
    @RequestMapping(value = "/activity/deleteUser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteUser(@PathVariable("id") int id) {
        int count = userservice.deleteUser(id);
        if (count == 1) {
            LOGGER.debug("deleteUser success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteUser failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("更新指定id的用户")
    @RequestMapping(value = "/activity/updateUser/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateUser(@PathVariable("id") int id,@RequestBody User user) {
        String password=user.getPassword();
        password=Sha256.getSHA256StrJava(password);
        user.setPassword(password);
        int count = userservice.updateUser(id,user);
        if (count == 1) {
            LOGGER.debug("updateUser success :id={}", id);
            return CommonResult.success("更新成功");
        } else {
            LOGGER.debug("updateUser failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/activity/changePassword/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult changepassword(@PathVariable("id") int id,
                                       @RequestParam(value = "oldPass") String oldPass,
                                       @RequestParam(value = "newPass") String newPass,
                                       @RequestParam(value = "newPass1") String newPass1) {
        int count;
        User user=userservice.getUser((long)id);
        if(user.getPassword().equals(Sha256.getSHA256StrJava(oldPass)) && newPass.equals(newPass1)) {
            user.setPassword(Sha256.getSHA256StrJava(newPass));
            count = userservice.changepassword(id, user);
        }
        else {
            LOGGER.debug("updateUser failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
        if (count == 1) {
            LOGGER.debug("updateUser success :id={}", id);
            return CommonResult.success("更新成功");
        } else {
            LOGGER.debug("updateUser failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("获取所有用户列表")
    @RequestMapping(value = "/activity/ListAllUser", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<User>> ListAllUser() {
        return CommonResult.success(userservice.ListAllUser());
    }

    @ApiOperation("获得指定id的活动")
    @RequestMapping(value = "/activity/getUserActivity", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getUserActivity(@RequestParam(value = "staffId") Long staffId) {
        CommonResult commonResult;
        List<Activity> activity = userservice.getUserActivity(staffId);
        commonResult = CommonResult.success(activity);
        return commonResult;
    }
}
