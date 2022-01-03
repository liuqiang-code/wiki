package com.bigstrong.wiki.req;

import lombok.Data;

/**
 * @author BigStrong
 * @date 2022/01/03
 * @description 分页查询请求对象
 */
@Data
public class PageReq {
    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页大小
     */
    private Integer size;
}
