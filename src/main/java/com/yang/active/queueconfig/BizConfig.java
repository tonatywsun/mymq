package com.yang.active.queueconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yangyang
 * @date 2019/8/30 19:41
 */
@Component
@ConfigurationProperties(prefix = "bizconfig")
public class BizConfig {
    private String queueName;

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        System.out.println(queueName);
        this.queueName = queueName;
    }
}
