package com.bigstrong.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigstrong.wiki.Resp.DocQueryResp;
import com.bigstrong.wiki.Resp.PageResp;
import com.bigstrong.wiki.domain.Doc;
import com.bigstrong.wiki.mapper.DocMapper;
import com.bigstrong.wiki.req.DocQueryReq;
import com.bigstrong.wiki.req.DocSaveReq;
import com.bigstrong.wiki.service.DocService;
import com.bigstrong.wiki.util.CopyUtil;
import com.bigstrong.wiki.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description DocService 实现类
 */
@Service
public class DocServiceImpl implements DocService {
    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    @Override
    public PageResp<DocQueryResp> getDocList(DocQueryReq req) {
        // 设置分页参数
        Page<Doc> docPage = new Page<>(req.getPage(), req.getSize());

        Page<Doc> docList = docMapper.selectPage(docPage, new LambdaQueryWrapper<Doc>().like(!ObjectUtils.isEmpty(req.getName()),
                Doc::getName, req.getName()));

        List<DocQueryResp> respList = CopyUtil.copyList(docList.getRecords(), DocQueryResp.class);

        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(docList.getTotal());
        pageResp.setData(respList);

        return pageResp;
    }

    @Override
    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            doc.setId(String.valueOf(snowFlake.nextId()));
            docMapper.insert(doc);
        } else {
            // 更新
            docMapper.updateById(doc);
        }
    }

    @Override
    public void delete(String id) {
        docMapper.deleteById(id);
    }

    @Override
    public List<DocQueryResp> allDoc() {
        List<Doc> categories = docMapper.selectList(new LambdaQueryWrapper<Doc>()
                .orderBy(true, true, Doc::getSort));
        return CopyUtil.copyList(categories, DocQueryResp.class);
    }
}
