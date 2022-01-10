package com.bigstrong.wiki.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description 电子书表DO对象
 */
@Data
@TableName("ebook")
public class Ebook {
    private String id;

    private String name;

    private String category1Id;

    private String category2Id;

    private String description;

    private String cover;

    private Integer docCount;

    private Integer viewCount;

    private Integer voteCount;
}
