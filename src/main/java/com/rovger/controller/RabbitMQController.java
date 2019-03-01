package com.rovger.controller;

import com.rovger.mq.MsgProducer_A;
import com.rovger.mq.MsgProducer_B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试RabbitMQ
 * @Author weijlu
 * @Date 2019/2/22 14:05
 */
@RestController
@RequestMapping(value = "/mq")
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.rovger.mq"})
public class RabbitMQController {

    @Autowired
    private MsgProducer_A producer_A;
    @Autowired
    private MsgProducer_B producer_B;

    @GetMapping("/send/{content}")
    public String sendMsg(@PathVariable String content) {
        int count = 50;
        while (count != 0) {
            if (count % 2 == 0) {
                producer_A.sendMsg("偶数：" + content + count);
            } else {
                producer_A.sendMsg("奇数：" + content + count);
            }
            count--;
        }
        return "send msg done!";
    }

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQController.class, args);
    }
}
