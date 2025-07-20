package com.soddong.stdy.sproutexample;

import com.soddong.stdy.sprout.annotation.AutoGet;
import com.soddong.stdy.sprout.annotation.AutoPost;
import com.soddong.stdy.sprout.annotation.RestEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RestEndpoint
public class SproutController {
    @AutoGet
    public String getUser(Long userId, boolean detailed) {
        return "User ID: " + userId + ", detailed: " + detailed;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Swagger!";
    }
}
