package com.bigstrong.wiki.req;

import lombok.Data;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description 查询 category 请求参数
 */
@Data
public class CategoryQueryReq extends PageReq {
    private String name;
}
