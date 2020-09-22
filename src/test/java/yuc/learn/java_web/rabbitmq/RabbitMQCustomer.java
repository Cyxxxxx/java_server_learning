package yuc.learn.java_web.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import yuc.learn.java_web.JavaWebApplicationTests;
import yuc.learn.java_web.message.DemoMessage;

/**
 * 消费者
 */
@Component
@RabbitListener(queues = DemoMessage.QUEUE)
public class RabbitMQCustomer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void msgCustomer(String msg){
        logger.info("msg:{}",msg);
        logger.info("消费完毕");
        // 消费完毕后，CDL计数-1
        JavaWebApplicationTests.CDL.countDown();
    }

}
