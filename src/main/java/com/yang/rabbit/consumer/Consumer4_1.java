package com.yang.rabbit.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.yang.rabbit.util.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Description: 路由模式
 * @Author: tona.sun
 * @Date: 2019/10/09 15:58
 */
public class Consumer4_1 {
    private final static String QUEUE_NAME = "q_yy_04_1";
    private final static String EXCHANGE_NAME = "e_yy_04";

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
        // 绑定队列到交换机 当routingKey和发送者相等是才会收到
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "del1");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "del2");
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
            System.out.println(" 消费着4-1获取到消息:" + message);
            //手动签收
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            Thread.sleep(100);
        }
    }
}
