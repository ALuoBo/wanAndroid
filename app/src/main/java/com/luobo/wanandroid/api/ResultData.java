package com.luobo.wanandroid.api;


public class ResultData<T> {

    private T data;
    private int errorCode;
    private String errorMsg;


    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        //请求成功返回数据,失败返回错误信息和错误码
        if (errorCode == 0) {
            return data;
        } else {
            throw new ApiException(errorMsg, errorCode);
        }
    }
}
