package com.hhzt.vod.api.repData;

/**
 * Created by wujichang on 2018/1/10.
 */

public class PayResultRep {

    private String code;
    private String msg;
    private String solution;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PayResultRep{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", solution='" + solution + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
