package cn.edu.zjut.common.service;

import cn.edu.zjut.common.api.CommonResult;

import java.util.Map;

/**
 * 验证码管理Service
 * Created by iris on 2020/12/30.
 */
public interface AuthCodeService {
    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);


}
