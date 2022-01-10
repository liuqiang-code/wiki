package com.bigstrong.wiki.req;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author BigStrong
 * @date 2022/01/10
 * @description 保存分类请求对象
 */
@Data
public class CategorySaveReq {
    private String id;

    private String parent;

    @NotNull(message = "名称不能为空")
    private String name;

    @NotNull(message = "排序不能为空")
    private Integer sort;
}
