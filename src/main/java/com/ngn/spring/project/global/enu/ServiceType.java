package com.ngn.spring.project.global.enu;

/**
 * Created by RMA on 3/10/2020.
 */
public enum ServiceType {

    //region enum
    ENGINEER("262a3f11-adbd-11e4-99d7-080027dcfac6"),
    ARCHITECTURE("36f9627a-adbd-11e4-99d7-080027dcfac6");
    //endregion
//region private variables
    private final String code;
    //endregion

    //region method
    private ServiceType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
