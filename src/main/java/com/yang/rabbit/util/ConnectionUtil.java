package com.yang.rabbit.util;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description: 获取连接工具类
 * @Author: tona.sun
 * @Date: 2019/10/09 15:50
 */
@Slf4j
public class ConnectionUtil {
    /**
     * @description :创建连接
     * @author : tona.sun
     * @date : 2019/10/10 11:51
     */
    public static Connection getConnection() {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //设置端口号
        factory.setPort(5672);
        //设置tVirtualHost
        factory.setVirtualHost("yy");
        //设置用户名
        factory.setUsername("yangyang");
        //设置密码
        factory.setPassword("ssss1111");
        //获取连接
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            log.error("get connection exception:{}", e.getMessage());
        } catch (TimeoutException e) {
            log.error("get connection exception:{}", e.getMessage());
        }
        return connection;
    }

    @Test
    public void test() {
        Connection connection = getConnection();
        System.out.println(connection.toString());
    }

}
