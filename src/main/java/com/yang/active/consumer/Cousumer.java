package com.yang.active.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author yangyang
 * @date 2019/8/28 14:50
 */
public class Cousumer {
    public static void main(String[] args) {
        try {
            cousumer("1");
            cousumer("2");
            cousumer("3");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String url = "tcp://127.0.0.1:61616";

    public static void cousumer(String i) throws Exception {
        //1.创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        //2.获取并开启连接
        Connection connection = factory.createConnection();
        connection.start();
        //是否开启事务 开启不提交则不会认为已经消费
        boolean transacted = false;
        //签收类型 AUTO_ACKNOWLEDGE:自动签收 消费者消费之后就算消费成功
        //CLIENT_ACKNOWLEDGE手动签收 消费者acknowledge后才认为消费成功
        int acknowledgeMode = Session.CLIENT_ACKNOWLEDGE;
        //3.创建会话
        Session session = connection.createSession(transacted, acknowledgeMode);
        //队列名称
        String queueName = "yangyang";
        //4.创建队列
        Queue queue = session.createQueue(queueName);
        //Topic topic = session.createTopic(topicname);
        //5.创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                String text = null;
                try {
                    text = textMessage.getText();
                    //手动签收
                    textMessage.acknowledge();
                    //session.commit();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                System.out.println(i + text);
            }
        });
    }
}
