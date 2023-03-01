package com.ilongli.springbootrocketmqdemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * Created by ilongli on 2023/2/28.
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "transaction-topic", consumerGroup = "my-consumer_test-topic-4")
public class Consumer4 implements RocketMQListener<String> {

    @Override
    public void onMessage(String data) {
        log.info("received data in transaction: {}", data);
    }

}
