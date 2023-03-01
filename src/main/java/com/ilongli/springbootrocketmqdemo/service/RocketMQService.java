package com.ilongli.springbootrocketmqdemo.service;

import com.ilongli.springbootrocketmqdemo.domain.User;

/**
 * Created by ilongli on 2023/2/28.
 */
public interface RocketMQService {


    void send(String topic, Object data);

    void sendAsync(String topic, User user);

    void sendOrderly(String topic, Object data, String hashKey);

    void sendInTransaction(String topic, Object data);

    String sendAndReceive(String topic, Object data);

    void sendAndReceiveAsync(String topic, Object data);

}
