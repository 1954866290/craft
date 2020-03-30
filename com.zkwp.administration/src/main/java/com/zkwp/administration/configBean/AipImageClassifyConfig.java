package com.zkwp.administration.configBean;

import com.baidu.aip.contentcensor.AipContentCensor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AipImageClassifyConfig {
    @Value("${baidu.AppID}")
    public String appId;

    @Value("${baidu.APIKey}")
    public String apiKey;

    @Value("${baidu.SecretKey}")
    public String secretKey;

    @Bean
    public AipContentCensor getAipImageClassify() {
        AipContentCensor  client = new AipContentCensor (appId, apiKey, secretKey);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }
}
