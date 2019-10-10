package com.yang.rabbit;


import com.yang.rabbit.producer.Producer1;
import com.yang.rabbit.producer.Producer2;
import org.junit.jupiter.api.Test;

/**
 * @Description: TODO
 * @Author: tona.sun
 * @Date: 2019/10/09 15:55
 */
public class MyTest {
    @Test
    public void test1() {
        for (Integer i = 0; i < 100; i++) {
            new Producer1().send(i.toString());
        }
    }

    @Test
    public void test2() {
        for (Integer i = 0; i < 100; i++) {
            new Producer2().send(i.toString());
        }
    }
}
