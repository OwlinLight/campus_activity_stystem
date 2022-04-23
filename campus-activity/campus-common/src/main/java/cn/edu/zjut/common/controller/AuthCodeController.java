package cn.edu.zjut.common.controller;

import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.service.AuthCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 验证码管理Controller
 * Created by iris on 2020/12/30.
 */
@Controller
@Api(tags = "AuthCodeController", description = "验证码管理")
public class AuthCodeController {
    @Autowired
    private AuthCodeService authCodeService;

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/activity/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return authCodeService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/activity/verifyAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        return authCodeService.verifyAuthCode(telephone, authCode);
    }
}
