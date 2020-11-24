package com.ngn.spring.project.cdb.certification;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.jfree.util.Log;

import javax.servlet.http.HttpServletRequest;
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
        final String cdbNo,
        final String initialRegistrationDate,
        final String ownerCID,
        final String regExpiryDate,
        final String dzongkhagName,
        final String ownerName,
        final HttpServletResponse response) {

            response.setContentType("application/pdf;charset=UTF-8");
            Log.debug("######## Enter PassportPrintPdfUtility[getJasperPrintForExporting] ");
            JasperPrint jasperprint = new JasperPrint();
            Map<String, Object> parameters = new HashMap<String, Object>();
            String filepath = "";

            String sideImageTopLeft = "";
            String sideImageTopRight = "";
            String imageTop = "";
            String imageBottom = "";
            String sideImageMiddleLeft = "";
            String sideImageMiddleRight = "";
            String sideImageBottomLeft = "";
            String sideImageBottomRight = "";
            String logo = "";

            try {
                sideImageTopLeft = url + "/jasperImages/cdbImages/topLeftBorder.jpg";
                sideImageTopRight = url + "/jasperImages/cdbImages/topRightBorder.jpg";
                imageTop = url + "/jasperImages/cdbImages/widthBar.jpg";
                imageBottom = url + "/jasperImages/cdbImages/widthBar.jpg";
                sideImageMiddleLeft = url + "/jasperImages/cdbImages/heightBar.jpg";
                sideImageMiddleRight = url + "/jasperImages/cdbImages/heightBar.jpg";
                sideImageBottomLeft = url + "/jasperImages/cdbImages/bottomLeftBorder.jpg";
                sideImageBottomRight = url + "/jasperImages/cdbImages/bottomRightBorder.jpg";
                logo = url + "/jasperImages/cdbImages/logo.GIF";

                parameters.put("URL", url);
                parameters.put("sideImageTopLeft", sideImageTopLeft);
                parameters.put("sideImageTopRight", sideImageTopRight);
                parameters.put("imageTop", imageTop);
                parameters.put("imageBottom", imageBottom);
                parameters.put("sideImageMiddleLeft", sideImageMiddleLeft);
                parameters.put("sideImageMiddleRight", sideImageMiddleRight);
                parameters.put("sideImageBottomLeft", sideImageBottomLeft);
                parameters.put("sideImageBottomRight", sideImageBottomRight);
                parameters.put("logo", logo);
                parameters.put("initialRegistrationDate", initialRegistrationDate);
                parameters.put("revalidationDate", initialRegistrationDate);
                parameters.put("ownerCID", ownerCID);
                parameters.put("cdbNo", cdbNo);
                parameters.put("regExpiryDate", regExpiryDate);
                parameters.put("ownerName", ownerName);
                parameters.put("dzongkhagName", dzongkhagName);

                if(cdbNo.startsWith("BA-")){
                    filepath = url + "/jasperFiles/architect.jasper";
                }
                if(cdbNo.startsWith("BS-")){
                    filepath = url + "/jasperFiles/surveyor.jasper";
                }
                if(cdbNo.startsWith("BE-")){
                    filepath = url + "/jasperFiles/engineer.jasper";
                }
                JasperReport jasperreport = (JasperReport) JRLoader.loadObjectFromFile(filepath);
                parameters.put(net.sf.jasperreports.engine.JRParameter.IS_IGNORE_PAGINATION, Boolean.FALSE);
                jasperprint = JasperFillManager.fillReport(jasperreport, parameters);
                Log.debug("######## Info PrintPdfUtility[getJasperPrintForExporting] Printing is Done!");

            }catch (Exception e){
                e.printStackTrace();
                Log.debug("######## Info PrintPdfUtility[getJasperPrintForExporting] Printing is Done!"+e);
            }
        Log.debug("######## Info PrintPdfUtility[getJasperPrintForExporting] Printing is Done!");
        return jasperprint;
    }
}
