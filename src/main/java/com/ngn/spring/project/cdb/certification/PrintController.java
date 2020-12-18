package com.ngn.spring.project.cdb.certification;

import com.ngn.spring.project.cdb.architect.services.ArchitectServices;
import com.ngn.spring.project.cdb.engineer.service.EngineerServices;
import com.ngn.spring.project.cdb.survey.service.SurveyServices;
import com.ngn.spring.project.cdb.trade.service.SpecializedService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping(value = "/print")
public class PrintController {

    @Autowired
    ArchitectServices architectServices;

    @Autowired
    SurveyServices services;

    @Autowired
    EngineerServices engineerServices;

    @Autowired
    SpecializedService specializedService;


    @RequestMapping(value = "/printCertificate", method = RequestMethod.GET)
    public void printCertificate (ModelMap model,HttpServletRequest request,HttpServletResponse response) {
        String cdbNo = request.getParameter("cdbNo");
        File filepath = null;
        String initialRegistrationDate = "";
        String revalidationDate = "";
        String ownerName = "";
        String ownerCID ="";
        String regExpiryDate ="";
        String firmName ="";
        String dzongkhagName ="";
        String newPhotoPath = null;

        JasperPrint jasperprint = new JasperPrint();
        CertificateDTO certificateDTO = new CertificateDTO();
        String url =null;
        try {
            if (cdbNo.startsWith("BA-") || cdbNo.startsWith("NBA-")) {
                cdbNo = request.getParameter("cdbNo");
                certificateDTO = architectServices.getArchitetPrintDetails(request, cdbNo);
                initialRegistrationDate = certificateDTO.getInitialRegistrationDate();
                cdbNo = certificateDTO.getCdbNo();
                ownerName = certificateDTO.getOwnerName();
                ownerCID = certificateDTO.getOwnerCID();
                regExpiryDate = certificateDTO.getRegExpiryDate();
                dzongkhagName = certificateDTO.getDzongkhagName();
            }
            if (cdbNo.startsWith("BS-") || cdbNo.startsWith("NBS-")) {
                cdbNo = request.getParameter("cdbNo");
                certificateDTO = services.getSurveyPrintDetails(request, cdbNo);
                initialRegistrationDate = certificateDTO.getInitialRegistrationDate();
                ownerName = certificateDTO.getOwnerName();
                ownerCID = certificateDTO.getOwnerCID();
                regExpiryDate = certificateDTO.getRegExpiryDate();
                dzongkhagName = certificateDTO.getDzongkhagName();
            }
            if (cdbNo.startsWith("BE-") || cdbNo.startsWith("NBE-")) {
                cdbNo = request.getParameter("cdbNo");
                certificateDTO = engineerServices.getEngineerPrintDetails(request, cdbNo);
                initialRegistrationDate = certificateDTO.getInitialRegistrationDate();
                ownerName = certificateDTO.getOwnerName();
                ownerCID = certificateDTO.getOwnerCID();
                regExpiryDate = certificateDTO.getRegExpiryDate();
                dzongkhagName = certificateDTO.getDzongkhagName();
            }
            if (cdbNo.startsWith("SP-")) {
                cdbNo = request.getParameter("cdbNo");
                certificateDTO = specializedService.getSpecializedTradePrintDetails(request, cdbNo);
                initialRegistrationDate = certificateDTO.getInitialRegistrationDate();
                ownerName = certificateDTO.getOwnerName();
                ownerCID = certificateDTO.getOwnerCID();
                regExpiryDate = certificateDTO.getRegExpiryDate();
                dzongkhagName = certificateDTO.getDzongkhagName();
            }
            PrintPDFUtility printPDFUtility = new PrintPDFUtility();
         //   url = request.getSession().getServletContext().getRealPath("/resources/JasperCertificate");
             filepath = new File(request.getSession().getServletContext().getRealPath("/resources/JasperCertificate"));

            jasperprint = printPDFUtility.getJasperPrintForExporting(
                    filepath.getPath(),
                    cdbNo,
                    initialRegistrationDate,
                    ownerCID,
                    regExpiryDate,
                    dzongkhagName,
                    ownerName,
                    response);

              ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/pdf;charset=UTF-8");

          if (cdbNo.startsWith("BA-") || cdbNo.startsWith("NBA-")) {
                response.setHeader("Content-Disposition", "attachment; filename=architect.pdf");
            }
            if (cdbNo.startsWith("BS-") || cdbNo.startsWith("NBS-")) {
                response.setHeader("Content-Disposition", "attachment; filename=Surveyor.pdf");
            }
            if (cdbNo.startsWith("BE-") || cdbNo.startsWith("NBE-")) {
                response.setHeader("Content-Disposition", "attachment; filename=Engineer.pdf");
            }
            if (cdbNo.startsWith("SP-") || cdbNo.startsWith("NSP-")) {
                response.setHeader("Content-Disposition", "attachment; filename=Engineer.pdf");
            }
          JasperExportManager.exportReportToPdfStream(jasperprint, out);
            out.flush();
            out.close();
        } catch (Exception e) {
           // return null;
        }
     //   return null;
    }
}
