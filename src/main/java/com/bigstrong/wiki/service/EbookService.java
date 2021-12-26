package com.bigstrong.wiki.service;

import com.bigstrong.wiki.Resp.EbookResp;
import com.bigstrong.wiki.req.EbookReq;

import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description ebookService 接口类
 */
public interface EbookService {
    /**
     * 获取ebook list
     * @param req 获取电子书请求参数
     * @return 电子书 list
     */
    List<EbookResp> getEbookList(EbookReq req);
}
