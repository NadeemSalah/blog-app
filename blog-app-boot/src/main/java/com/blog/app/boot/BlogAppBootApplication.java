package com.blog.app.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.blog.app.*"
})
public class BlogAppBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAppBootApplication.class, args);
    }
}
