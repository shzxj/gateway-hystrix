package com.xxx.bigdata.api.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Thread.sleep;

@Slf4j
@RestController
public class HystrixController {

    @GetMapping("/fallback")
    public String fallback() {
        return "熔断测试成功！";
    }

    @GetMapping("/test")
    public String test() {
        try {
            sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test";
    }
}
