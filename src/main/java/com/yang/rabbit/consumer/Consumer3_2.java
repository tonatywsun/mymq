package com.yang.rabbit.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.yang.rabbit.util.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Description: 订阅模式
 * @Author: tona.sun
 * @Date: 2019/10/09 15:58
 */
public class Consumer3_2 {
    private final static String QUEUE_NAME = "q_yy_03_2";
    private final static String EXCHANGE_NAME = "e_yy_03";

    @Test
    public void test() {
        try {
            consum();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consum() throws IOException, InterruptedException {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
        // 同一时刻服务器只会发一条消息给消费者,等签收之后再发下一条
        channel.basicQos(1);
        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME, false, consumer);
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" 消费着2-1获取到消息:" + message);
            //手动签收
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            Thread.sleep(200);
        }
    }
}
