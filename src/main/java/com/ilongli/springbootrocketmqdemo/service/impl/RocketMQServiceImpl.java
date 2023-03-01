package com.ilongli.springbootrocketmqdemo.service.impl;

import com.ilongli.springbootrocketmqdemo.domain.User;
import com.ilongli.springbootrocketmqdemo.service.RocketMQService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by ilongli on 2023/2/28.
 * https://github.com/apache/rocketmq-spring/wiki/%E5%8F%91%E9%80%81%E6%B6%88%E6%81%AF
 */
@Slf4j
@Service
public class RocketMQServiceImpl implements RocketMQService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    @Override
    public void send(String topic, Object data) {
        rocketMQTemplate.convertAndSend(topic, data);
    }

    @Override
    public void sendAsync(String topic, User user) {
        rocketMQTemplate.asyncSend(topic, user, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("async onSucess SendResult=%s %n", sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.printf("async onException Throwable=%s %n", throwable);
            }
        });
    }

    @Override
    public void sendOrderly(String topic, Object data, String hashKey) {
        rocketMQTemplate.syncSendOrderly(topic, data, hashKey);
    }

    @Override
    public void sendInTransaction(String topic, Object data) {
        Message msg = MessageBuilder.withPayload(data).build();
        rocketMQTemplate.sendMessageInTransaction(topic, msg, "test-tran");
    }

    @Override
    public String sendAndReceive(String topic, Object data) {

        return rocketMQTemplate.sendAndReceive(topic, data, String.class, 8000L);
    }

    @Override
    public void sendAndReceiveAsync(String topic, Object data) {

        rocketMQTemplate.sendAndReceive(topic, data, new RocketMQLocalRequestCallback<String>() {
            @Override
            public void onSuccess(String message) {
                log.info("send data and receive: {}", message);
            }

            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
            }
        }, 8000L);

    }
}
