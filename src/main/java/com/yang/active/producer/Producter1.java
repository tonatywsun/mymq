package com.yang.active.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author yangyang
 * @date 2019/8/30 19:34
 */
@Component
public class Producter1 {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    @Scheduled(fixedDelay = 5000)
    public void send() {
        jmsMessagingTemplate.convertAndSend(queue, "");
    }
}
