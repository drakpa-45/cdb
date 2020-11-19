package com.ngn.spring.project.cdb.certification;

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

    @RequestMapping(value = "/printCertificate", method = RequestMethod.GET)
    public String printCertificate (ModelMap model,HttpServletRequest request,HttpServletResponse response) {

        String applicationNo = request.getParameter("refNo");
        try {
            JasperPrint jasperprint;
            response.setContentType("application/pdf");
            //response.setHeader("Content-Disposition","attachment; filename=Architect_Certificate" + ".pdf");
            response.setHeader("Content-Disposition", "filename=" + applicationNo + ".pdf");
            ServletOutputStream out = response.getOutputStream();
            ReportPermitUtility reportsUtility = new ReportPermitUtility(request.getSession().getServletContext().getRealPath("/"));
            JRHtmlExporter jrhtml = new JRHtmlExporter();
            jasperprint = reportsUtility.printSurveyorCertificate(applicationNo);
            JasperExportManager.exportReportToPdfStream(jasperprint, out);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
