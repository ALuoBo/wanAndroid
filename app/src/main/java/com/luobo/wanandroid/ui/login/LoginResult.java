package com.luobo.wanandroid.ui.login;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {

    private LoggedInUserView success;

    private String error;

    LoginResult(String error) {
        this.error = error;
    }

    LoginResult(LoggedInUserView success) {
        this.success = success;
    }


    LoggedInUserView getSuccess() {
        return success;
    }


    String getError() {
        return error;
    }
}