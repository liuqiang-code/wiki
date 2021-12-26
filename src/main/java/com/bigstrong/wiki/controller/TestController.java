package com.bigstrong.wiki.controller;

import com.bigstrong.wiki.domain.Test;
import com.bigstrong.wiki.service.TestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/24
 * @description 测试 Controller
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello world!";
    }

    @PostMapping("/hello/post")
    public String helloPost(String name) {
        return "hello world! post " + name;
    }

    @RequestMapping("test/list")
    public List<Test> list() {
        return testService.list();
    }
}
