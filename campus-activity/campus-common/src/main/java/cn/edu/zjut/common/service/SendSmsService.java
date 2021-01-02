package cn.edu.zjut.common.service;

import java.util.Map;

/**
 * 阿里云短信服务
 * Created by iris on 2020/12/31.
 */
public interface SendSmsService {
    boolean sendSms(String telephone, String templateCode, Map<String, Object> templateParam);
}
