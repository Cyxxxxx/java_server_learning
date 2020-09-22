package yuc.learn.java_web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuc.learn.java_web.mapper.UserMapper;
import yuc.learn.java_web.pojo.po.UserPO;
import yuc.learn.java_web.pojo.vo.StudentTestVo;

import java.util.List;

@RestController
@RequestMapping("/api")
@RunWith(SpringRunner.class)
@SpringBootTest
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
        StudentTestVo studentTest = new StudentTestVo();
        studentTest.setName("yuc");
        studentTest.setId(1001L);
        studentTest.setGrade(100);
        return studentTest.toString();
    }

    @GetMapping("/ptj")
    public String POJOToJson(){
        StudentTestVo studentTest = new StudentTestVo();
        studentTest.setName("yuc");
        studentTest.setId(1001L);
        studentTest.setGrade(100);
        return JSON.toJSONString(studentTest);
    }

    @GetMapping("/jtp")
    public String jsonToPOJO(){
        String jsonStr = "{\"grade\":100,\"id\":1001,\"name\":\"yuc\"}";
        return JSONObject.parseObject(jsonStr,StudentTestVo.class).toString();
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void mpTest(){
        List<UserPO> userPOList = userMapper.selectList(null);
        userPOList.forEach(System.out::println);
    }

    @Test
    public void updateTest(){
        userMapper.updatePasswordById("789",1L);
    }
}
