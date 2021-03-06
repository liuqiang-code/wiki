package com.bigstrong.wiki.req;

import lombok.Data;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description ebook 请求参数
 */
@Data
public class EbookQueryReq extends PageReq {
    private String id;

    private String name;
}
