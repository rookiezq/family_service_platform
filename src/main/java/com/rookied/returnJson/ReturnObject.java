package com.rookied.returnJson;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhangqiang
 * @date 2021/8/7
 */
public class ReturnObject {
    /**
     * 消息默认为空
     */
    private String message = "";

    /**
     * 响应状态码默认200
     */
    private Integer code = 200;

    /**
     * 响应结果
     */
    private Object result;

    public ReturnObject() {
    }

    public ReturnObject(Object result) {
        this.result = result;
    }

    public ReturnObject(String message, Integer code, Object result) {
        this.message = message;
        this.code = code;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public ReturnObject setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ReturnObject setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public ReturnObject setResult(Object result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
