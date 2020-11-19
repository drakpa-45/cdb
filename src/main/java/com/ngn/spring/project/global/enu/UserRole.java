package com.ngn.spring.project.global.enu;

/**
 * ==================================================================================
 * Created by user on 3/12/2020.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */
public enum UserRole {

    CONTRACTOR(1,"Contractors"),
    CONSULTANT(2,"Consultant"),
    ARCHITECT(3,"Architects"),
    SPTRADE(4,"Specialized Trades"),
    ENGINEER(5,"Engineers"),
    PROCURING_AGENCY_ETOOL(6,"Procuring Agency (Etool)"),
    PROCURING_AGENCY_CINET(7,"Procuring Agency (CiNet)"),
    CONTRACTOR_VERIFIER(8,"CRPS Contractor Verifier"),
    CONTRACTOR_APPROVER(9,"CRPS Contractor Approver"),
    ARCHITECT_VERIFIER(10,"CRPS Architect Verifier"),
    ARCHITECT_APPROVER(11,"CRPS Architect Approver"),
    ENGINEER_VERIFIER(12,"CRPS Engineer Verifier"),
    ENGINEER_APPROVER(13,"CRPS Engineer Approver"),
    CONSULTANT_VERIFIER(14,"CRPS Consultant Verifier"),
    CONSULTANT_APPROVER(15,"CRPS Consultant Approver"),
    SP_VERIFIER(16,"CRPS Specialized Trade Verifier"),
    SP_APPROVER(17,"CRPS Specialized Trade Approver"),
    PAYMENT_APPROVER(18,"Payment Approver for Registration"),
    REGISTRATION_SERVICES(19,"Registration Services"),
    WEB_SECRETARIAT(20,"Web Secretariat"),
    WEB_NEWS(21,"Web News &amp; Circular"),
    SERVICES(22,"services"),
    PHOTO_GALLERY(23,"Web Secretariat"),
    SMS(25,"SMS"),
    WEB_TRAINING(26,"Web Training Forms"),
    PRESS_RELEASE(27,"Replace-Release-e-Tool"),
    WEB_DOWNLOADS(28,"Web Downloads"),
    AUDIT_REPORT(29,"Audit Report"),
    HR_EQUIPMENT(30,"HR and Equipment Check"),
    ADVERSE_COMMENTS(31,"Adverse and Comments"),
    AGENCY_HEAD_ACCESS(32,"Head of Agency Access"),
    ACCOUNTS_REPORTS(33,"Accounts Reports"),
    CONTRACTORS(34,"Contractors"),
    RESEARCH_REPORTS(35,"Research Reports"),
    LEGAL_ACCESS(36,"Legal Access"),
    ADMINISTRATOR(37,"Administrator"),
    MONITORING_REPORTS(38,"Monitoring Reports"),
    AUDIT_CLEARANCE(39,"Audit clearance"),
    REPORTS(40,"Reports"),
    E_TOOL_REPORTS(41,"E-Tool Reports"),
    ARBITRATION(42,"Arbitration"),
    VERIFIER(43,"Verifier"),
    APPROVER(44,"Approver");


    private final int refNo;
    private final String roleName;

    //region method
    private UserRole(int refNo,String roleName) {
        this.refNo = refNo;
        this.roleName = roleName;
    }

    public int getRefNo() {
        return refNo;
    }
    public String getRoleName() {
        return roleName;
    }

    //endregion
}
