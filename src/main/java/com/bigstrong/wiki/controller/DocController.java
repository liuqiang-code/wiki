package com.bigstrong.wiki.controller;

import com.bigstrong.wiki.Resp.CommonResp;
import com.bigstrong.wiki.req.DocQueryReq;
import com.bigstrong.wiki.req.DocSaveReq;
import com.bigstrong.wiki.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description 文档管理Controller
 */
@RestController
@RequestMapping("/doc")
public class DocController {
    @Autowired
    private DocService docService;

    @RequestMapping("/list")
    public CommonResp getDocList(@Valid DocQueryReq req) {
        return CommonResp.success(docService.getDocList(req));
    }

    @RequestMapping("/all")
    public CommonResp all() {
        return CommonResp.success(docService.allDoc());
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq req) {
        docService.save(req);
        return CommonResp.success();
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable String id) {
        docService.delete(id);
        return CommonResp.success();
    }
}
