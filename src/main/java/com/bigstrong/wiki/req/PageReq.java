package com.bigstrong.wiki.req;

import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author BigStrong
 * @date 2022/01/03
 * @description 分页查询请求对象
 */
@Data
public class PageReq {
    /**
     * 页码
     */
    @NotNull(message = "【页码】不能为空")
    private Integer page;

    /**
     * 每页大小
     */
    @NotNull(message = "【每页条数】不能为空")
    @Max(value = 1000, message = "【每页条数】不能超过1000")
    private Integer size;
}
