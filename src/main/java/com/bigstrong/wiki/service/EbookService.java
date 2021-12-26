package com.bigstrong.wiki.service;

import com.bigstrong.wiki.domain.Ebook;

import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description ebookService 接口类
 */
public interface EbookService {
    /**
     * 获取ebook list
     * @return
     */
    List<Ebook> getEbookList();
}
