package com.bigstrong.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bigstrong.wiki.Resp.EbookResp;
import com.bigstrong.wiki.domain.Ebook;
import com.bigstrong.wiki.mapper.EbookMapper;
import com.bigstrong.wiki.req.EbookReq;
import com.bigstrong.wiki.service.EbookService;
import com.bigstrong.wiki.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public List<EbookResp> getEbookList(EbookReq req) {
        List<Ebook> ebooks = ebookMapper.selectList(new LambdaQueryWrapper<Ebook>().like(!StringUtils.isEmpty(req.getName()),
                Ebook::getName, req.getName()));
        return CopyUtil.copyList(ebooks, EbookResp.class);
    }
}
