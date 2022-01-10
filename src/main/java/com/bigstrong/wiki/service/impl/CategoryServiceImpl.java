package com.bigstrong.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigstrong.wiki.Resp.CategoryQueryResp;
import com.bigstrong.wiki.Resp.PageResp;
import com.bigstrong.wiki.domain.Category;
import com.bigstrong.wiki.mapper.CategoryMapper;
import com.bigstrong.wiki.req.CategoryQueryReq;
import com.bigstrong.wiki.req.CategorySaveReq;
import com.bigstrong.wiki.service.CategoryService;
import com.bigstrong.wiki.util.CopyUtil;
import com.bigstrong.wiki.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description CategoryService 实现类
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    @Override
    public PageResp<CategoryQueryResp> getCategoryList(CategoryQueryReq req) {
        // 设置分页参数
        Page<Category> categoryPage = new Page<>(req.getPage(), req.getSize());

        Page<Category> categoryList = categoryMapper.selectPage(categoryPage, new LambdaQueryWrapper<Category>().like(!ObjectUtils.isEmpty(req.getName()),
                Category::getName, req.getName()));

        List<CategoryQueryResp> respList = CopyUtil.copyList(categoryList.getRecords(), CategoryQueryResp.class);

        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(categoryList.getTotal());
        pageResp.setData(respList);

        return pageResp;
    }

    @Override
    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            category.setId(String.valueOf(snowFlake.nextId()));
            categoryMapper.insert(category);
        } else {
            // 更新
            categoryMapper.updateById(category);
        }
    }

    @Override
    public void delete(String id) {
        categoryMapper.deleteById(id);
    }
}
