package com.bigstrong.wiki.controller;

import com.bigstrong.wiki.Resp.CommonResp;
import com.bigstrong.wiki.req.EbookQueryReq;
import com.bigstrong.wiki.req.EbookSaveReq;
import com.bigstrong.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description
 */
@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @RequestMapping("/list")
    public CommonResp getEbookList(EbookQueryReq req) {
        return CommonResp.success(ebookService.getEbookList(req));
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        ebookService.save(req);
        return CommonResp.success();
    }
}
