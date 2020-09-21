package yuc.learn.java_web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuc.learn.java_web.pojo.vo.StudentTest;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "Hello World!";
    }

    /**
     * lombok用例
     * @return
     */
    @GetMapping("/stu")
    public String studentShow(){
        StudentTest studentTest = new StudentTest();
        studentTest.setName("yuc");
        studentTest.setId(1001L);
        studentTest.setGrade(100);
        return studentTest.toString();
    }

}
