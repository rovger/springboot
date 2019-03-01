package com.rovger.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author weijlu
 * @Date 2019/2/22 13:47
 */
@Component
@RabbitListener(queues = {RabbitMQConfig.QUEUE_A, RabbitMQConfig.QUEUE_B})
public class MsgConsumer_A {

    @RabbitHandler
    public void process(String content) {
        System.out.println("接收处理队列A当中的消息："+ content);
    }
}
