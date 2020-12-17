package cn.edu.zjut.common.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource({"classpath:ossconfig.properties"})
@Configuration
public class OssConfig {
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.accessKeyId}")
    private String ALIYUN_OSS_ACCESSKEYID;
    @Value("${aliyun.oss.accessKeySecret}")
    private String ALIYUN_OSS_ACCESSKEYSECRET;

    public OssConfig() {
    }

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(this.ALIYUN_OSS_ENDPOINT, this.ALIYUN_OSS_ACCESSKEYID, this.ALIYUN_OSS_ACCESSKEYSECRET);
    }
}
