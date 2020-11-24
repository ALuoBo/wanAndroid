package com.luobo.wanandroid.ui.login;

public class LoggedInUser {

    /**
     * data : null
     * errorCode : -1
     * errorMsg : 账号密码不匹配！
     */

    private Data data;
    private int errorCode;
    private String errorMsg;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    static class Data {
        private String nickname;
        private String username;

        public String getNickname() {
            return nickname;
        }

        public String getUsername() {
            return username;
        }
    }
}
