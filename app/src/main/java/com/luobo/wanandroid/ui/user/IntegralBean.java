package com.luobo.wanandroid.ui.user;

public class IntegralBean {

    /**
     * data : {"coinCount":108,"level":2,"rank":"5540","userId":81512,"username":"a**obobo"}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;
    private int errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * coinCount : 108
         * level : 2
         * rank : 5540
         * userId : 81512
         * username : a**obobo
         */

        private int coinCount;
        private int level;
        private String rank;
        private int userId;
        private String username;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
