package com.bigstrong.wiki.service;

import com.bigstrong.wiki.Resp.EbookQueryResp;
import com.bigstrong.wiki.Resp.PageResp;
import com.bigstrong.wiki.req.EbookQueryReq;
import com.bigstrong.wiki.req.EbookSaveReq;

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
    PageResp<EbookQueryResp> getEbookList(EbookQueryReq req);

    /**
     * 保存电子书
     * @param req 保存电子书请求对象
     */
    void save(EbookSaveReq req);

    /**
     * 删除电子书
     * @param id 电子书id
     */
    void delete(String id);
}
