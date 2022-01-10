package com.bigstrong.wiki.service;

import com.bigstrong.wiki.Resp.CategoryQueryResp;
import com.bigstrong.wiki.Resp.PageResp;
import com.bigstrong.wiki.req.CategoryQueryReq;
import com.bigstrong.wiki.req.CategorySaveReq;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description categoryService 接口类
 */
public interface CategoryService {
    /**
     * 获取category list
     * @param req 获取类别请求参数
     * @return 类别 list
     */
    PageResp<CategoryQueryResp> getCategoryList(CategoryQueryReq req);

    /**
     * 保存类别
     * @param req 保存类别请求对象
     */
    void save(CategorySaveReq req);

    /**
     * 删除类别
     * @param id 类别id
     */
    void delete(String id);
}
