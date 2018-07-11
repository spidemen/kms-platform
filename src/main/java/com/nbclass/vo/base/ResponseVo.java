package com.nbclass.vo.base;
/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
public class ResponseVo<T> {
    private String status;
    private String msg;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseVo(String status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

}
