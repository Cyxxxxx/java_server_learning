package yuc.learn.java_web.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import yuc.learn.java_web.message.DemoMessage;

/**
 * 生产者
 */
@Component
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 消息发送
     */
    public void msgSend(){
        String msg = "Hello rabbitMQ!";
        rabbitTemplate.convertAndSend(DemoMessage.QUEUE,msg);
        logger.info("消息发送成功");
    }



}
