package com.soddong.stdy.sproutexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.soddong.stdy"
})
public class SproutExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SproutExampleApplication.class, args);
    }

}
