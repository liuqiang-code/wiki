package com.bigstrong.wiki.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author BigStrong
 * @date 2022/01/23
 * @description 文档表DO对象
 */
@Data
public class DocSaveReq {
    private Long id;

    @NotNull(message = "【电子书】不能为空")
    private Long ebookId;

    @NotNull(message = "【父文档】不能为空")
    private Long parent;

    @NotNull(message = "【名称】不能为空")
    private String name;

    @NotNull(message = "【排序】不能为空")
    private Long sort;

    private Long viewCount;

    private Long voteCount;
}
