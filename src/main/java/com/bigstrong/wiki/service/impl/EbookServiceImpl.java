package com.bigstrong.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigstrong.wiki.Resp.EbookResp;
import com.bigstrong.wiki.Resp.PageResp;
import com.bigstrong.wiki.domain.Ebook;
import com.bigstrong.wiki.mapper.EbookMapper;
import com.bigstrong.wiki.req.EbookReq;
import com.bigstrong.wiki.service.EbookService;
import com.bigstrong.wiki.util.CopyUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
    public PageResp<EbookResp> getEbookList(EbookReq req) {
        // 设置分页参数
        Page<Ebook> ebookPage = new Page<>(req.getPage(), req.getSize());

        Page<Ebook> ebookList = ebookMapper.selectPage(ebookPage, new LambdaQueryWrapper<Ebook>().like(Objects.nonNull(req.getName()),
                Ebook::getName, req.getName()));

        List<EbookResp> respList = CopyUtil.copyList(ebookList.getRecords(), EbookResp.class);

        PageResp<EbookResp> pageResp = new PageResp<>();
        pageResp.setTotal(ebookList.getTotal());
        pageResp.setData(respList);

        return pageResp;
    }
}
