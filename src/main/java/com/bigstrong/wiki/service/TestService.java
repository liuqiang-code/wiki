package com.bigstrong.wiki.service;

import com.bigstrong.wiki.domain.Test;
import com.bigstrong.wiki.mapper.TestMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/25
 * @description
 */
@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}
