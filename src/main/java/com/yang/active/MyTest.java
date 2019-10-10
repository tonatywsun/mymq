package com.yang.active;

import com.yang.active.producer.Producer;

/**
 * @author yangyang
 * @date 2019/8/28 14:39
 */
public class MyTest {
    public static void main(String[] args) throws Exception {
        Producer producer = new Producer();
        for (int i = 0; i < 100; i++) {
            producer.send("yangyang" + i);
        }
    }
}
