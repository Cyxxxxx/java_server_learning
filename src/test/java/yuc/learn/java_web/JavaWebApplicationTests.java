package yuc.learn.java_web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yuc.learn.java_web.mapper.UserMapper;
import yuc.learn.java_web.service.UserService;
import yuc.learn.java_web.pojo.po.UserPO;

import java.util.List;
import yuc.learn.java_web.rabbitmq.RabbitMQProducer;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class JavaWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void queryAllTest(){
        List<UserPO> userPOList = userMapper.selectList(null);
        userPOList.forEach(System.out::println);
    }

    @Test
    public void querySpeRow(){
        List<UserPO> userPOList = userMapper.selectList(new QueryWrapper<UserPO>().le("id",10L));
        userPOList.forEach(System.out::println);
    }

    @Test
    public void updateTest(){
        userMapper.updatePasswordById("789",1L);
        UserPO userPO = userMapper.selectById(1L);
        System.out.println(userPO.toString());
    }

    @Test
    public void mpUpdateByIdTest(){
        UserPO userPO = new UserPO();
        userPO.setId(1L);
        userPO.setPassword("456");
        userMapper.updateById(userPO);
        System.out.println(userMapper.selectById(1L).toString());
    }

    @Test
    public void mpUpdateTest(){
        UserPO userPO = new UserPO();
        userPO.setPassword("abc");
        // 等价于 UPDATE tb_user SET `password`='abc' WHERE `user_name`='yuc';
        userMapper.update(
                userPO,
                new QueryWrapper<UserPO>().eq("user_name","yuc")
        );
        System.out.println(userMapper.selectById(1L).toString());
    }

    @Test
    public void insertTest(){
        UserPO userPO = new UserPO();
        userPO.setUserName("SH");
        userPO.setPassword("123");
        userMapper.insert(userPO);
        queryAllTest();
    }

    public void delTest(){
        // TODO
    }

    @Test
    public void serviceTest(){
        System.out.println(userService.getById(1L).toString());
    }




    /**
     * 消息生产者
     */
    @Autowired
    RabbitMQProducer producer;

    /**
     * 消息数
     */
    private static int MSG_COUNT = 5;

    /**
     * 根据消息数阻塞线程，直到所有消费者都收到消息
     */
    public static CountDownLatch CDL = new CountDownLatch(MSG_COUNT);

    @Test
    public void MQTest() throws InterruptedException {
        // 发送指定的消息数，代表收到了多少请求
        for(;MSG_COUNT>0;--MSG_COUNT){
            producer.msgSend();
        }
        // 阻塞等待，保证消费
        CDL.await();
    }

}
