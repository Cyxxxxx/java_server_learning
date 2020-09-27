package yuc.learn.java_web.exception.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import yuc.learn.java_web.exception.MyException;

import java.util.concurrent.CountDownLatch;

@RestControllerAdvice
public class DemoExceptionHandler {


    @ExceptionHandler(MyException.class)
    public String myExceptionHandler(MyException exception){
        System.out.println("收到抛出的异常啦！");
        System.out.println("异常信息："+exception.getMessage());
        return exception.getMessage();
    }

}
