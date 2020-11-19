
package com.ngn.spring.project.global.enu;


public enum LoginError {
    //region enum
    FAILED("login.failed"),
    LOCKED("login.locked"),
    MAX_SESSION("login.maxSession"),
    EXPIRED("login.expired");
    //endregion
//region private variables
    private final String code;
    //endregion

    //region method
    private LoginError(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    //endregion
}
