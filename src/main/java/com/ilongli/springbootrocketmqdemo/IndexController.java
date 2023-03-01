package com.ilongli.springbootrocketmqdemo;

import com.ilongli.springbootrocketmqdemo.domain.User;
import com.ilongli.springbootrocketmqdemo.service.RocketMQService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by ilongli on 2023/2/28.
 */
@RestController
public class IndexController {

    @Resource
    private RocketMQService rocketMQService;

    @GetMapping("")
    public String index() {
        return "spring-boot rocketmq demo.";
    }

    @GetMapping("test1")
    public String test1() {
        rocketMQService.send("test-topic-1", "hello rocketmq!");
        return "[test1]ok";
    }

    @GetMapping("test2")
    public String test2() {
        User user = new User();
        user.setName("jack");
        user.setAge(24);
        rocketMQService.sendAsync("test-topic-2", user);
        return "[test2]ok";
    }

    @GetMapping("test3")
    public String test3() {

        for (int i = 0; i < 10; i++) {
            rocketMQService.sendOrderly("orderly-topic", "data-" + i, UUID.randomUUID().toString());
        }

        return "[test3]ok";
    }


    @GetMapping("test4")
    public String test4() {
        rocketMQService.sendInTransaction("transaction-topic", "transaction-data");
        return "[test4]ok";
    }


    @GetMapping("test5")
    public String test5() {
        return rocketMQService.sendAndReceive("receive-topic", "need-reply-data");
    }


    @GetMapping("test6")
    public String test6() {
        rocketMQService.sendAndReceiveAsync("receive-topic-async", "need-reply-data-async");
        return "[test6]ok";
    }

}
