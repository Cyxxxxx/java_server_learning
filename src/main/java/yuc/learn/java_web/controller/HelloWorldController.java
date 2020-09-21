package yuc.learn.java_web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuc.learn.java_web.pojo.vo.StudentTestVo;

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

}
