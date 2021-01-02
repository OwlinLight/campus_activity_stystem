package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.api.CommonResult;
import cn.edu.zjut.common.service.AuthCodeService;
import cn.edu.zjut.common.service.RedisService;
import cn.edu.zjut.common.service.SendSmsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

import static cn.edu.zjut.common.api.CommonResult.success;

/**
 * 验证码管理Service实现类
 */
@Service
public class AuthCodeServiceImpl implements AuthCodeService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    @Autowired
    private SendSmsService sendSmsService;

    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        HashMap<String, Object> templateParam = new HashMap<>();
        templateParam.put("code", sb.toString());
        boolean isSend = sendSmsService.sendSms(telephone, "SMS_208580448", templateParam);
        if (isSend) {
            redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
            redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
            return CommonResult.success(sb.toString(), "获取验证码成功");
        } else {
            return CommonResult.failed("发送失败");
        }

    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return success(null, "验证码校验成功");
        } else {
            return CommonResult.failed("验证码错误");
        }
    }
}
