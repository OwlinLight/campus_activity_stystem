package cn.edu.zjut.common.service;


/**
 * 腾讯云短信服务
 * Created by iris on 2020/12/31.
 */
public interface SendSmsService {
    public boolean sendSms(String[] phoneNumberSet1, String[] templateParamSet1, String templateID);
}
