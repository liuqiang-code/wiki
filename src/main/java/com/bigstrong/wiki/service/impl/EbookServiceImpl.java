package com.bigstrong.wiki.service.impl;

import com.bigstrong.wiki.domain.Ebook;
import com.bigstrong.wiki.mapper.EbookMapper;
import com.bigstrong.wiki.service.EbookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description EbookService 实现类
 */
@Service
public class EbookServiceImpl implements EbookService {
    @Resource
    private EbookMapper ebookMapper;

    @Override
    public List<Ebook> getEbookList() {
        return ebookMapper.selectList(null);
    }
}
