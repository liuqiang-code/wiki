package com.bigstrong.wiki.Resp;

import lombok.Data;

/**
 * @author BigStrong
 * @date 2022/01/23
 * @description 文档表DO对象
 */
@Data
public class DocQueryResp {
    private String id;

    private Long ebookId;

    private Long parent;

    private String name;

    private Long sort;

    private Long viewCount;

    private Long voteCount;
}
