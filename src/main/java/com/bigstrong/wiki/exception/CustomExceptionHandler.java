package com.bigstrong.wiki.exception;

import com.bigstrong.wiki.Resp.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author BigStrong
 * @date 2022/01/08
 * @description 自定义全局异常处理
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {
    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResp validExceptionHandler(BindException e) {
        log.warn("参数校验失败：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return CommonResp.error(null, e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
