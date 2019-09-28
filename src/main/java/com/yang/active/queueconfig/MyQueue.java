package com.yang.active.queueconfig;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Queue;


/**
 * @author yangyang
 * @date 2019/8/30 19:25
 */
@Component
public class MyQueue {
    @Value("${bizconfig.queueName}")
    public String queue_name;

    @Bean
    public Queue getQueue() {
        return new ActiveMQQueue(queue_name);
    }
}
