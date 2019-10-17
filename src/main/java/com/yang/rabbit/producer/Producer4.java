package com.yang.rabbit.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yang.rabbit.util.ConnectionUtil;
import org.junit.jupiter.api.Test;

/**
 * @Description: 路由方式
 * @Author: tona.sun
 * @Date: 2019/10/09 15:53
 */
public class Producer4 {
    private final static String EXCHANGE_NAME = "e_yy_04";

    @Test
    public void test() {
        new Producer4().send("test");
    }

    public void send(String message) {
        Connection connection = null;
        Channel channel = null;
        try {
            // 获取到连接
            connection = ConnectionUtil.getConnection();
            // 从连接中创建通道
            channel = connection.createChannel();
            // 创建exchange
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            //发送
            channel.basicPublish(EXCHANGE_NAME, "add11", null, message.getBytes());
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
