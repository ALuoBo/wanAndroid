package com.luobo.wanandroid.api;

class ApiException extends RuntimeException {
    int errorCode;
    public ApiException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    public int getErrorCode(){
        return  errorCode;
    }
}
