package com.bigstrong.wiki.Resp;

import lombok.Data;

/**
 * @author BigStrong
 * @date 2022/01/10
 * @description 查询分类响应对象
 */
@Data
public class CategoryQueryResp {
    private String id;

    private String parent;

    private String name;

    private Integer sort;
}
