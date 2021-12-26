package com.bigstrong.wiki.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description
 */
@TableName("demo")
public class Demo {
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
