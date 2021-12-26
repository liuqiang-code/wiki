package com.bigstrong.wiki.service;

import com.bigstrong.wiki.domain.Demo;
import com.bigstrong.wiki.mapper.DemoMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description
 */
@Service
public class DemoService {
    @Resource
    private DemoMapper demoMapper;

    public List<Demo> list() {
        return demoMapper.selectList(null);
    }
}
