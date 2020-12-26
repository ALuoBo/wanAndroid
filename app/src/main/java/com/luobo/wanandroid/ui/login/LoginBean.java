package com.luobo.wanandroid.ui.login;

public class LoginBean {
    /**
     * data : null
     * errorCode : -1
     * errorMsg : 账号密码不匹配！
     */

    private Data data;
    private int errorCode;
    private String errorMsg;

    public void setData(Data data) {
        this.data = data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Data getData() {
        return data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public class Data {

        private String nickname;
        private String username;

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public String getUsername() {
            return username;
        }
    }
}
