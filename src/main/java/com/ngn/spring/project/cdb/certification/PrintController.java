package com.ngn.spring.project.cdb.certification;

import com.ngn.spring.project.cdb.architect.services.ArchitectServices;
import com.ngn.spring.project.cdb.engineer.service.EngineerServices;
import com.ngn.spring.project.cdb.survey.service.SurveyServices;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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

    @RequestMapping(value = "/printCertificate", method = RequestMethod.GET)
    public String printCertificate (ModelMap model,HttpServletRequest request,HttpServletResponse response, CertificateDTO certificateDTO) {
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
        String url =null;
        try {
            if (cdbNo.startsWith("BA-")) {
                cdbNo = request.getParameter("cdbNo");
                certificateDTO = architectServices.getArchitetPrintDetails(request, cdbNo);
                initialRegistrationDate = certificateDTO.getInitialRegistrationDate();
                ownerName = certificateDTO.getOwnerName();
                ownerCID = certificateDTO.getOwnerCID();
                regExpiryDate = certificateDTO.getRegExpiryDate();
                dzongkhagName = certificateDTO.getDzongkhagName();
            }
            if (cdbNo.startsWith("BS-")) {
                cdbNo = request.getParameter("cdbNo");
                certificateDTO = services.getSurveyPrintDetails(request, cdbNo);
                initialRegistrationDate = certificateDTO.getInitialRegistrationDate();
                ownerName = certificateDTO.getOwnerName();
                ownerCID = certificateDTO.getOwnerCID();
                regExpiryDate = certificateDTO.getRegExpiryDate();
                dzongkhagName = certificateDTO.getDzongkhagName();
            }
            if (cdbNo.startsWith("BE-")) {
                cdbNo = request.getParameter("cdbNo");
                certificateDTO = engineerServices.getEngineerPrintDetails(request, cdbNo);
                initialRegistrationDate = certificateDTO.getInitialRegistrationDate();
                ownerName = certificateDTO.getOwnerName();
                ownerCID = certificateDTO.getOwnerCID();
                regExpiryDate = certificateDTO.getRegExpiryDate();
                dzongkhagName = certificateDTO.getDzongkhagName();
            }
            PrintPDFUtility printPDFUtility = new PrintPDFUtility();
            url = request.getSession().getServletContext().getRealPath("/resources/JasperCertificate");
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

            response.setHeader("Content-Disposition", "attachment; filename=CERTIFICATE_Architect.pdf");
            response.setHeader("Content-Disposition", "attachment; filename=CERTIFICATE_Surveyor.pdf");
            response.setHeader("Content-Disposition", "attachment; filename=CERTIFICATE_Engineer.pdf");

            JasperExportManager.exportReportToPdfStream(jasperprint, out);
            out.flush();
            out.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
