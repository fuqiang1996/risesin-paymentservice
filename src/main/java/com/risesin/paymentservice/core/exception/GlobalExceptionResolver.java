package com.risesin.paymentservice.core.exception;

import com.risesin.common.vo.resultVo.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/10
 * @DESCRIPTION 处理异常
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    /**
     * 处理所有不可知异常
     * @param e 异常
     * @return json结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handleException(Exception e) {
        // 打印异常堆栈信息
        log.error(e.getMessage(), e);
        return R.ER();
    }

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(PayException.class)
    @ResponseBody
    public R handlePayException(PayException e) {
        // 打印异常堆栈信息
        log.error(e.getMessage(), e);
        return R.ER(e.getErrcode(),e.getErrmsg());
    }
}
