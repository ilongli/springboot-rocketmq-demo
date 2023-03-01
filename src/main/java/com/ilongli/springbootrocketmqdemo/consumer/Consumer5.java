package com.ilongli.springbootrocketmqdemo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Service;

/**
 * Created by ilongli on 2023/2/28.
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "receive-topic", consumerGroup = "my-consumer_test-topic-5")
public class Consumer5 implements RocketMQReplyListener<String, String> {


    @Override
    public String onMessage(String message) {

        log.info("received data and reply: {}", message);

        return "reply string";
    }
}
