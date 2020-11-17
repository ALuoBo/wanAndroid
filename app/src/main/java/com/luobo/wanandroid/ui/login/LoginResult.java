package com.luobo.wanandroid.ui.login;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {

    private LoggedInUser success;

    private String error;

    LoginResult(String error) {
        this.error = error;
    }

    LoginResult(LoggedInUser success) {
        this.success = success;
    }

    LoggedInUser getSuccess() {
        return success;
    }

    String getError() {
        return error;
    }
}