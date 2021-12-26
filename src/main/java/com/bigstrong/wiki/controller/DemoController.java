package com.bigstrong.wiki.controller;

import com.bigstrong.wiki.domain.Demo;
import com.bigstrong.wiki.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description
 */
@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;

    @RequestMapping("/demo/list")
    public List<Demo> list() {
        return demoService.list();
    }
}
