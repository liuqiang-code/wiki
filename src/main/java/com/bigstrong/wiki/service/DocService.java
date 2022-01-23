package com.bigstrong.wiki.service;

import com.bigstrong.wiki.Resp.DocQueryResp;
import com.bigstrong.wiki.Resp.PageResp;
import com.bigstrong.wiki.req.DocQueryReq;
import com.bigstrong.wiki.req.DocSaveReq;

import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description docService 接口类
 */
public interface DocService {
    /**
     * 获取doc list
     * @param req 获取文档请求参数
     * @return 文档 list
     */
    PageResp<DocQueryResp> getDocList(DocQueryReq req);

    /**
     * 保存文档
     * @param req 保存文档请求对象
     */
    void save(DocSaveReq req);

    /**
     * 删除文档
     * @param id 文档id
     */
    void delete(String id);

    /**
     * 查询所有文档
     * @return 所有文档
     */
    List<DocQueryResp> allDoc();
}
