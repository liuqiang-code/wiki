package com.bigstrong.wiki.controller;

import com.bigstrong.wiki.Resp.CommonResp;
import com.bigstrong.wiki.domain.Ebook;
import com.bigstrong.wiki.service.EbookService;
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
@RequestMapping("/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @RequestMapping("/list")
    public CommonResp getEbookList() {
        List<Ebook> ebookList = ebookService.getEbookList();
        return CommonResp.success(ebookList);
    }
}
