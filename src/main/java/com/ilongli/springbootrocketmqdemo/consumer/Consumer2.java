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
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class Consumer2 implements RocketMQListener<User> {

    @Override
    public void onMessage(User user) {
        log.info("received user: {}", user);
    }

}
