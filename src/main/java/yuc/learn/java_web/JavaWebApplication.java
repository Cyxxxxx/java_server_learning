package yuc.learn.java_web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("yuc.learn.java_web.mapper")
public class JavaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaWebApplication.class, args);
    }

}
