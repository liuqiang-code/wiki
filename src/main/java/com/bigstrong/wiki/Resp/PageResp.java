package com.bigstrong.wiki.Resp;

import lombok.Data;
import java.util.List;

/**
 * @author BigStrong
 * @date 2022/01/03
 * @description 分页查询响应对象
 */
@Data
public class PageResp<T> {
    /**
     * 总数
     */
    private long total;

    /**
     * 数据
     */
    private List<T> data;
}
