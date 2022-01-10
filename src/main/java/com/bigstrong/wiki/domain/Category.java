package com.bigstrong.wiki.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author BigStrong
 * @date 2022/01/10
 * @description 类别表DO对象
 */
@Data
@TableName("category")
public class Category {
    /**
     * 类别id
     */
    private String id;

    /**
     * 父类id
     */
    private String parent;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;
}
