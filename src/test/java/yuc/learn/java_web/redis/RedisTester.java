package yuc.learn.java_web.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yuc.learn.java_web.component.reids.RedisUtil;
import yuc.learn.java_web.pojo.po.UserPO;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class RedisTester {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void redisSetter() throws InterruptedException {
        UserPO userPO = new UserPO();
        userPO.setUserName("abc");
        userPO.setPassword("123");
        userPO.setProfile("Hello Redis!");
        redisUtil.set("user",userPO,2);
        redisGetter();
    }


    public void redisGetter() throws InterruptedException {
        // 沉睡3秒
        Thread.sleep(3000);
        System.out.println(redisUtil.get("user", UserPO.class));
    }

}
