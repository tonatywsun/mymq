package com.yang.active.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author yangyang
 * @date 2019/8/28 14:25
 */
public class Producer {
    public static final String url = "tcp://127.0.0.1:61616";

    public void send(String message) throws Exception {
        //1.创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        //2.获取并开启连接
        Connection connection = factory.createConnection();
        connection.start();
        //是否开启事务 开启不提交则不会把消息放到队列
        boolean transacted = true;
        //签收类型 AUTO_ACKNOWLEDGE:自动签收 消费者消费之后就算消费成功
        //CLIENT_ACKNOWLEDGE手动签收 消费者acknowledge后才认为消费成功  以消费者为准
        int acknowledgeMode = Session.AUTO_ACKNOWLEDGE;
        //3.创建会话
        Session session = connection.createSession(transacted, acknowledgeMode);
        //队列名称
        String queueName = "yangyang";
        //4.创建队列
        Queue queue = session.createQueue(queueName);
        //Topic topic = session.createTopic(topicname);
        //5.创建生产者
        MessageProducer producer = session.createProducer(queue);
        //设置是否持久化 默认持久化PERSISTENT
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //创建信息
        TextMessage textMessage = session.createTextMessage(message);
        //发送
        producer.send(textMessage);
        //transacted为true时提交事务
        session.commit();
        connection.close();
    }
}
