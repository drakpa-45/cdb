package com.ngn.spring.project.cdb.certification;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.jfree.util.Log;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pema Drakpa on 7/22/2020.
 */
public  class PrintPDFUtility {

    String url = null;

    public final JasperPrint getJasperPrintForExporting(
        final String url,
        final String applNo,
        final String name,
        final String cid,
        final String signDate,
        final String cpCategory,
        final String employerName,
        final String employerLocation,
        final String dzongkhag,
        final String registrationNo,
        final String registrationDate,
        final String photo,
        final String certExpiryDate,
        final String expiry_Date,
        final String address,
        final String issuedDate,
        final String manufacturingCompany,
        final String Drug_category,
        final String firmName,
        final String ta_type,

        HttpServletResponse response) {

            response.setContentType("application/pdf;charset=UTF-8");
            Log.debug("######## Enter PassportPrintPdfUtility[getJasperPrintForExporting] ");
            JasperPrint jasperprint = new JasperPrint();
            Map<String, Object> parameters = new HashMap<String, Object>();
            String filepath = "";

            String company_logo1 = "";
            String company_logo2 = "";
            String company_department = "";
            String waterMark = "";

            try {
                company_logo1 = url + "/jasperImages/CompetentPersonCertificateImages/company_logo1.jpg";
                company_logo2 = url + "/jasperImages/CompetentPersonCertificateImages/company_logo2.jpg";
                company_department = url + "/jasperImages/CompetentPersonCertificateImages/company_department.jpg";
                waterMark = url + "/jasperImages/CompetentPersonCertificateImages/dra_logo.png";
                parameters.put("URL", url);
                parameters.put("Company_Logo1", company_logo1);
                parameters.put("Company_Department", company_department);
                parameters.put("Company_Logo2", company_logo2);
                parameters.put("PassportPhoto", photo);
                parameters.put("name", name);
                parameters.put("cid", cid);
                parameters.put("location", address);
                parameters.put("Sign_Date", LocalDate.now());
                parameters.put("RegistrationNo", registrationNo);
                parameters.put("RegistrationDate", registrationDate);
                parameters.put("CertExpiryDate", certExpiryDate);
                parameters.put("WATER_MARK", waterMark);
                parameters.put("manufacturer", manufacturingCompany);
                parameters.put("expiryDate", expiry_Date);
                parameters.put("ApplicationNo", applNo);
                parameters.put("FirmName", firmName);
                parameters.put("taType", ta_type);
                parameters.put("cpRegistrationNo", registrationNo);
                parameters.put("fromDate", issuedDate);
                parameters.put("toDate", certExpiryDate);
                //parameters.put("Dzongkhag", address);
                parameters.put("RegistrationDate",  LocalDate.now());
                parameters.put("emplName", name);
                //parameters.put("emplLocation", address);
               // parameters.put("RegistrationDate", signDate);
               // parameters.put("location", employerLocation);
                parameters.put("expiryDate", certExpiryDate);
                parameters.put("regDate",LocalDate.now());
                parameters.put("IssueDate",LocalDate.now());
                parameters.put("cpName", name);
                parameters.put("cpRegNo", registrationDate);



                if(applNo.startsWith("121")){
                    filepath = url + "/jasperFiles/CompetentPersonCertificateJasper/CPRegistrationCertificate.jasper";
                }
                else if(applNo.startsWith("125")){
                    if (Drug_category.equalsIgnoreCase("GU")){
                        filepath = url + "/jasperFiles/importAuthDrug/ImportAuthorization.jasper";
                    }
                    else if (Drug_category.equalsIgnoreCase("SR")){
                        filepath = url + "/jasperFiles/importAuthDrug/ImportAuthorization.jasper";
                    }
                    else if (Drug_category.equalsIgnoreCase("PU")){
                        filepath = url + "/jasperFiles/importAuthDrug/ImportAuthorization.jasper";
                    }
                    else if (Drug_category.equalsIgnoreCase("NR")){
                        filepath = url + "/jasperFiles/importAuthDrug/ImportAuthorization.jasper";
                    }
                }
                else if(applNo.startsWith("122")){
                    filepath = url + "/jasperFiles/CompetentPersonCertificateJasper/CompetentPersonCertificatePvt.jasper";
                }
                else if(applNo.startsWith("123")){
                    filepath = url + "/jasperFiles/ChangeOfTechnicalAuthorizationDetailsCertificate/ChangeOwnership&LocationApprv.jasper";
                }
                else if(applNo.startsWith("127")){
                    filepath = url + "/jasperFiles/TechnicalAuthorizationCertificate/ProvisionalTAM.jasper";
                }
                else if(applNo.startsWith("128")){
                    filepath = url + "/jasperFiles/TechnicalAuthorizationRenewalCertificate/TASaleDistribution.jasper";
                }
                JasperReport jasperreport = (JasperReport) JRLoader.loadObjectFromFile(filepath);
                parameters.put(net.sf.jasperreports.engine.JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
                jasperprint = JasperFillManager.fillReport(jasperreport, parameters);
                Log.debug("######## Info PassportPrintPdfUtility[getJasperPrintForExporting] Printing is Done!");

            }catch (Exception e){
                e.printStackTrace();
            }
    return jasperprint;
    }
}
