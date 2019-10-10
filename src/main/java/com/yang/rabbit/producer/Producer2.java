package com.yang.rabbit.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yang.rabbit.util.ConnectionUtil;
import org.junit.jupiter.api.Test;

/**
 * @Description: 简单队列发送者
 * @Author: tona.sun
 * @Date: 2019/10/09 15:53
 */
public class Producer2 {
    private final static String QUEUE_NAME = "q_yy_02";

    @Test
    public void test() {
        new Producer2().send("test");
    }

    public void send(String message) {
        Connection connection = null;
        Channel channel = null;
        try {
            // 获取到连接
            connection = ConnectionUtil.getConnection();
            // 从连接中创建通道
            channel = connection.createChannel();
            // 创建队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //发送
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭通道和连接
            try {
                channel.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
