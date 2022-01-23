package com.bigstrong.wiki.req;

import lombok.Data;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description 查询 doc 请求参数
 */
@Data
public class DocQueryReq extends PageReq {
    private String name;
}
