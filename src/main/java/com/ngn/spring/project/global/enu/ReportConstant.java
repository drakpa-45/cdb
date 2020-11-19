package com.ngn.spring.project.global.enu;

/**
 * Created by USER on 10/8/2020.
 */
public class ReportConstant {
    //PRINTING THE CERTIFICATE
    public static final String  ARCHITECT_CERTIFICATE ="/Pages/cdb/certificate/Architect/ArchitectCertificate.jasper";
    public static final String ARCHITECT_INFORMATION="/Pages/cdb/certificate/Architect/architectInformation.jasper";
    public static final String ENGINEER_CERTIFICATE="/Pages/cdb/certificate/Architect/EngineerCertificate.jasper";
    public static final String ENGINEER_INFORMATION="/Pages/cdb/certificate/Architect/EngineerInformation.jasper";
    public static final String SPECIALIZED_TRADE_CERTIFICATE="/Pages/cdb/certificate/Trade/TradeCertificate.jasper";
    public static final String CONSULTANT_CERTIFICATE="/Pages/cdb/certificate/Consultant/ConsultantCertificate.jasper";
    public static final String CONTRACTOR_CERTIFICATE="/Pages/cdb/certificate/Contractor/ContratorCertificate.jasper";
    public static final String CONTRACTOR_PRINTINFORMATION="/Pages/cdb/certificate/Contractor/Contratorinformation.jasper";
    public static final String CONSULTANT_PRINTINFORMATION="/Pages/cdb/certificate/Consultant/Consultantinformation.jasper";
    public static final String DATASOURCENAMECDB="java:comp/env/jdbc/CDBDB";
    public static final String DATASOURCENAMECDBMASTER="java:comp/env/jdbc/CDBMASTERDB";
    private String constants;
    ReportConstant(String constants)
    {
        this.constants = constants;
    }
    public String getQuery() {
        return constants;
    }
}