package com.risesin.paymentservice.core.exception;

import com.risesin.common.exception.GlobalException;
import com.risesin.common.vo.resultVo.RC;

/**
 * @AUTHOR Baby
 * @CREATE 2019/10/10
 * @DESCRIPTION 支付异常
 * @since 1.0.0
 */
public class PayException extends GlobalException {
    public PayException() {
    }

    public PayException(String errmsg) {
        super(errmsg);
    }

    public PayException(Integer errcode, String errmsg) {
        super(errcode, errmsg);
    }

    public PayException(String errmsg, Throwable e) {
        super(errmsg, e);
    }

    public PayException(Integer errcode, String errmsg, Throwable e) {
        super(errcode, errmsg, e);
    }

    public PayException(RC rc) {
        super(rc);
    }

    public PayException(RC rc, Throwable e) {
        super(rc, e);
    }
}
