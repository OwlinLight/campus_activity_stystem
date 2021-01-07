package cn.edu.zjut.common.service.impl;

import cn.edu.zjut.common.service.SendSmsService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.stereotype.Service;


/**
 * 腾讯云短信服务实现类
 * Created by iris on 2020/12/31.
 */
@Service
public class SendSmsServiceImpl implements SendSmsService {
    @Override
    public boolean sendSms(String[] phoneNumberSet1, String[] templateParamSet1, String templateID) {
        try {

            Credential cred = new Credential("AKIDZzCEdttjGanIXgD57z4pCJDuSbWNHYy9", "5xY6OTLxIi5G7lJekYM0NSHxnRshIjtq");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            SendSmsRequest req = new SendSmsRequest();
            req.setPhoneNumberSet(phoneNumberSet1);


            req.setTemplateParamSet(templateParamSet1);

            req.setTemplateID(templateID);
            req.setSmsSdkAppid("1400469443");
            req.setSign("SanneKyofus");

            SendSmsResponse resp = client.SendSms(req);

            System.out.println(SendSmsResponse.toJsonString(resp));
            return true;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
        return false;
    }
}
