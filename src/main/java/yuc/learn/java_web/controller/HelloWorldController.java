package yuc.learn.java_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuc.learn.java_web.component.reids.RedisUtil;
import yuc.learn.java_web.pojo.vo.StudentTest;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    /**
     * Redis工具类
     */
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello World!";
    }

    /**
     * lombok+redis 组合用例
     * @return
     */
    @GetMapping("/stu")
    public String studentShow(){
        StudentTest studentTest = new StudentTest();
        studentTest.setName("yuc");
        studentTest.setId(1001L);
        studentTest.setGrade(100);

        // redis get/set
        redisUtil.set("stuTest", studentTest , 30);
        return redisUtil.get("stuTest", StudentTest.class).toString();
    }

}
