package com.bsm.fast.common.exception;


import lombok.Data;


/**
 * 自定义异常
 *
 * @author limingxin
 */
@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1214634330184022334L;
    private String msg;

    private int code = 500;

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BizException(String msg, Throwable e) {
        super(msg,e);
        this.msg = msg;
    }

    public BizException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BizException(String msg, int code,Throwable e) {
        super(msg,e);
        this.msg = msg;
        this.code = code;
    }

}
