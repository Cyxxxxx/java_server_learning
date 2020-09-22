package yuc.learn.java_web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yuc.learn.java_web.rabbitmq.RabbitMQProducer;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class JavaWebApplicationTests {

    @Test
    void contextLoads() {
    }




    /**
     * 消息生产者
     */
    @Autowired
    RabbitMQProducer producer;

    /**
     * 消息数
     */
    private static int MSG_COUNT = 1;

    /**
     * 根据消息数阻塞线程，直到所有消费者都收到消息
     */
    public static CountDownLatch CDL = new CountDownLatch(MSG_COUNT);

    @Test
    public void MQTest() throws InterruptedException {
        producer.msgSend();
        // 阻塞等待，保证消费
        CDL.await();
    }

}
