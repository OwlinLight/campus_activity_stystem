package cn.edu.zjut.common.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.*;;

/**
 * Created by iris on 2021/1/3.
 */
public class SendSms {
    public static void main(String[] args) {
        try {

            Credential cred = new Credential("AKIDZzCEdttjGanIXgD57z4pCJDuSbWNHYy9", "5xY6OTLxIi5G7lJekYM0NSHxnRshIjtq");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);

            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {"+8619858180151"};
            req.setPhoneNumberSet(phoneNumberSet1);


            String[] templateParamSet1 = {"123456", "5"};
            req.setTemplateParamSet(templateParamSet1);

            req.setTemplateID("832776");
            req.setSmsSdkAppid("1400469443");
            req.setSign("SanneKyofus");

            SendSmsResponse resp = client.SendSms(req);

            System.out.println(SendSmsResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

}
