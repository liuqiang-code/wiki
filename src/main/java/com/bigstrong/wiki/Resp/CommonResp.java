package com.bigstrong.wiki.Resp;

import lombok.Data;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description 公共返回类
 */
@Data
public class CommonResp<T> {
    /**
     * 业务上的成功或失败
     */
    private Boolean success = true;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据，自定义类型
     */
    private T data;

    public CommonResp(T data) {
        this.data = data;
    }

    public CommonResp(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public static <T> CommonResp<T> success(T data) {
        return new CommonResp(data);
    }

    public static <T> CommonResp<T> success(T data, String message) {
        return new CommonResp(data, message);
    }

    public static <T> CommonResp<T> error(T data, String message) {
        CommonResp commonResp = new CommonResp(data, message);
        commonResp.setSuccess(false);
        return commonResp;
    }
}
