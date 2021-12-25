package com.bigstrong.wiki.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BigStrong
 * @date 2021/12/24
 * @description 测试 Controller
 */
@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello world!";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "hello world! post " + name;
    }
}
