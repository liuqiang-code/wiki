package com.bigstrong.wiki.mapper;

import com.bigstrong.wiki.domain.Test;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/25
 * @description
 */
@Mapper
public interface TestMapper {
    public List<Test> list();
}
