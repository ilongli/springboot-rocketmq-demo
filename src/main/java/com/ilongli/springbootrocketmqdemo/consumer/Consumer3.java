package com.ilongli.springbootrocketmqdemo.consumer;

import com.ilongli.springbootrocketmqdemo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * Created by ilongli on 2023/2/28.
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "orderly-topic", consumerGroup = "my-consumer_test-topic-3")
public class Consumer3 implements RocketMQListener<String> {

    @Override
    public void onMessage(String data) {
        log.info("received data orderly: {}", data);
    }

}
