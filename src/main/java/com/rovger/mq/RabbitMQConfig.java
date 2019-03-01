package com.rovger.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Description:
 *
 *
    Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
    Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
    Queue:消息的载体,每个消息都会被投到一个或多个队列。
    Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
    Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
    vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
    Producer:消息生产者,就是投递消息的程序.
    Consumer:消息消费者,就是接受消息的程序.
    Channel:消息通道,在客户端的每个连接里,可建立多个channel.
 *
 * @Author weijlu
 * @Date 2019/2/22 11:03
 */
@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_A = "spring-boot-exchange_A";
    public static final String EXCHANGE_B = "spring-boot-exchange_B";

    public static final String QUEUE_A = "spring-boot-queue_A";
    public static final String QUEUE_B = "spring-boot-queue_B";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private Boolean publisherConfirms;

    @Value("${spring.rabbitmq.virtual-host}")
    private String vHost;

    /**
     * 创建 ConnectionFactory
     *
     * @return
     * @throws Exception
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.host, this.port);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.vHost);
        connectionFactory.setPublisherConfirms(this.publisherConfirms); //必须要设置
        return connectionFactory;
    }

    /**
     * RabbitMQ template
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(this.connectionFactory());
        //template.setConfirmCallback(); 设置消息确认
        //template.setReturnCallback();
        return template;
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_A);
    }

    /**
     * 获取队列A
     * @return
     */
    @Bean
    public Queue queue_A() {
//        return new Queue(QUEUE_A, true); 队列持久
        return QueueBuilder.durable(QUEUE_A).build();
    }

    /**
     * 获取队列B
     * @return
     */
    @Bean
    public Queue queue_B() {
        return QueueBuilder.durable(QUEUE_B).build();
    }

    /**
     * 绑定，一个交换机可以绑定多个队列，消息可以通过一个交换机，分发到不同的队列当中
     * @return
     */
    @Bean
    public Binding binding_A() {
        return BindingBuilder.bind(this.queue_A()).to(this.defaultExchange()).with(ROUTINGKEY_A);
    }

    @Bean
    public Binding binding_B() {
        return BindingBuilder.bind(this.queue_B()).to(this.defaultExchange()).with(ROUTINGKEY_B);
    }
}
