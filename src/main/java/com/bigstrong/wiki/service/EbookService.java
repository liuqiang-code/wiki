package com.bigstrong.wiki.service;

import com.bigstrong.wiki.Resp.EbookResp;
import com.bigstrong.wiki.Resp.PageResp;
import com.bigstrong.wiki.req.EbookReq;

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
    PageResp<EbookResp> getEbookList(EbookReq req);
}
