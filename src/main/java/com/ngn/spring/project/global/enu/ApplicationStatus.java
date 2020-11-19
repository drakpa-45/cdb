package com.ngn.spring.project.global.enu;

/**
 * ==================================================================================
 * Created by user on 2/12/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public enum ApplicationStatus {
    //region enum
    UNDER_PROCESS("262a3f11-adbd-11e4-99d7-080027dcfac6","Under Process"),//when user submit
    VERIFIED("36f9627a-adbd-11e4-99d7-080027dcfac6","Verified"),//when verifier verified
    APPROVED("463c2d4c-adbd-11e4-99d7-080027dcfac6","Approved"),//when after payment is done
    APPROVED_FOR_PAYMENT("6195664d-c3c5-11e4-af9f-080027dcfac6","Approved for Payment"),//when approver approves
    DEREGISTERED("b1fa6607-b1dd-11e4-89f3-080027dcfac6","De-registered"),
    REJECTED("de662a61-b049-11e4-89f3-080027dcfac6","Rejected"),
    BLACKLISTED("f89a6f55-b1dd-11e4-89f3-080027dcfac6","Blacklisted"),
    REVOKED("f89a6f55-b1dd-aac4-89f3-080027dcfac6","Revoked"),
    SUSPENDED("f89a6f55-b1dd-xvid-89f3-080027dcfac6","Suspended"),
    DEBARRED("nomedia5-b1dd-xvid-89f3-080027dcfac6","Debarred"),
    REGISTRATION("55a922e1-cbbf-11e4-83fb-080027dcfac6","Registration"),
    RENEWAL("45bc628b-cbbe-11e4-83fb-080027dcfac6","Renewal"),
    CANCELLATION("acf4b324-cbbe-11e4-83fb-080027dcfac6","Cancellation"),
    SURRENDERED("nomedia5-b1dd-xvid-89f3-080027dcfac6","Surrendered");
    //endregion
//region private variables
    private final String code;
    private final String name;
    //endregion
    //region method
    private ApplicationStatus(String code,String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    //endregion
}

