package com.bigstrong.wiki.req;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @author BigStrong
 * @date 2022/01/04
 * @description 保存电子书请求对象
 */
@Data
public class EbookSaveReq {
    private String id;

    @NotNull(message = "名称不能为空")
    private String name;

    private String category1Id;

    private String category2Id;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;
}
