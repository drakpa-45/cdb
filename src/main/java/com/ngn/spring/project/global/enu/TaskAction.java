package com.ngn.spring.project.global.enu;

/**
 * ==================================================================================
 * Created by user on 2/14/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public enum TaskAction {
    //region enum
    CONTRACTOR_APPLICATION("262a3f11-adbd-11e4-99d7-080027dcfac6"),
    VERIFIED("36f9627a-adbd-11e4-99d7-080027dcfac6"),
    APPROVED("463c2d4c-adbd-11e4-99d7-080027dcfac6"),
    APPROVED_PAYMENT("6195664d-c3c5-11e4-af9f-080027dcfac6"),
    DEREGISTERED("b1fa6607-b1dd-11e4-89f3-080027dcfac6"),
    REJECTED("de662a61-b049-11e4-89f3-080027dcfac6"),
    BLACKLISTED("f89a6f55-b1dd-11e4-89f3-080027dcfac6"),
    REVOKED("f89a6f55-b1dd-aac4-89f3-080027dcfac6"),
    SUSPENDED("f89a6f55-b1dd-xvid-89f3-080027dcfac6"),
    DEBARRED("nomedia5-b1dd-xvid-89f3-080027dcfac6"),
    SURRENDERED("nomedia5-b1dd-xvid-89f3-080027dcfac6");
    //endregion
//region private variables
    private final String code;
    //endregion

    //region method
    private TaskAction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    //endregion
}
