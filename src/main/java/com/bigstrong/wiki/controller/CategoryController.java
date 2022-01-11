package com.bigstrong.wiki.controller;

import com.bigstrong.wiki.Resp.CommonResp;
import com.bigstrong.wiki.req.CategoryQueryReq;
import com.bigstrong.wiki.req.CategorySaveReq;
import com.bigstrong.wiki.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description 类别管理Controller
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public CommonResp getCategoryList(@Valid CategoryQueryReq req) {
        return CommonResp.success(categoryService.getCategoryList(req));
    }

    @RequestMapping("/all")
    public CommonResp all() {
        return CommonResp.success(categoryService.allCategory());
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req) {
        categoryService.save(req);
        return CommonResp.success();
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable String id) {
        categoryService.delete(id);
        return CommonResp.success();
    }
}
