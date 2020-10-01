package xyz.zerotower.learn.pages;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("xyz.zerotower.learn.pages.dao")
public class PagesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagesApplication.class, args);
    }

}
