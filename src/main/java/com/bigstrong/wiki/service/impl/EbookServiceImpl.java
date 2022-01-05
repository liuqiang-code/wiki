package com.bigstrong.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigstrong.wiki.Resp.EbookQueryResp;
import com.bigstrong.wiki.Resp.PageResp;
import com.bigstrong.wiki.domain.Ebook;
import com.bigstrong.wiki.mapper.EbookMapper;
import com.bigstrong.wiki.req.EbookQueryReq;
import com.bigstrong.wiki.req.EbookSaveReq;
import com.bigstrong.wiki.service.EbookService;
import com.bigstrong.wiki.util.CopyUtil;
import com.bigstrong.wiki.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    @Resource
    private SnowFlake snowFlake;

    @Override
    public PageResp<EbookQueryResp> getEbookList(EbookQueryReq req) {
        // 设置分页参数
        Page<Ebook> ebookPage = new Page<>(req.getPage(), req.getSize());

        Page<Ebook> ebookList = ebookMapper.selectPage(ebookPage, new LambdaQueryWrapper<Ebook>().like(!ObjectUtils.isEmpty(req.getName()),
                Ebook::getName, req.getName()));

        List<EbookQueryResp> respList = CopyUtil.copyList(ebookList.getRecords(), EbookQueryResp.class);

        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(ebookList.getTotal());
        pageResp.setData(respList);

        return pageResp;
    }

    @Override
    public void save(EbookSaveReq req) {
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            ebook.setId(String.valueOf(snowFlake.nextId()));
            ebookMapper.insert(ebook);
        } else {
            // 更新
            ebookMapper.updateById(ebook);
        }
    }
}
