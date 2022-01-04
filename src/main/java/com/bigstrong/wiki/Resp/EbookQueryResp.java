package com.bigstrong.wiki.Resp;

import lombok.Data;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description 获取ebook响应参数
 */
@Data
public class EbookQueryResp {
    private Integer id;

    private String name;

    private String category1Id;

    private String category2Id;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;
}
