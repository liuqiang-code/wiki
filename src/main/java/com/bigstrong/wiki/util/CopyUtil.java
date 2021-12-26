package com.bigstrong.wiki.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BigStrong
 * @date 2021/12/26
 * @description 复制工具类
 */
public class CopyUtil {
    /**
     * 单体复制
     */
    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }

    /**
     * 列表复制
     */
    public static <T> List<T> copyList(List source, Class<T> clazz) {
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            for (Object c: source) {
                T obj = copy(c, clazz);
                target.add(obj);
            }
        }
        return target;
    }
}
