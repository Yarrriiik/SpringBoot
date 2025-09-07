package com.example.transport.app;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.example.transport")
public class TransportApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TransportApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);
        ConsoleApp console = ctx.getBean(ConsoleApp.class);
        console.run();
    }
}
