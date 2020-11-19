package com.ngn.spring.project.cdb.certification;

import com.ngn.spring.project.global.enu.ReportConstant;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 10/8/2020.
 */
public class ReportPermitUtility {
    String url = null;
    String leftSideBar = "";
    String rightSideBar = "";
    String topBar = "";
    String bottomBar = "";
    String architectLogo = "";

    public ReportPermitUtility(String realPath) {
        this.url = url;
        System.out.println("url " + url);
    }

    public JasperPrint printSurveyorCertificate(String applicationNo) {
        JasperPrint jasperprint = new JasperPrint();
        String filepath = "";
        String seal = null;
        String background = null;
        String sign = null;
        Map parameters = new HashMap();

        leftSideBar=url+"/Pages/certificate/Consultant/Images/left2.png";
        rightSideBar=url+"/Pages/certificate/Consultant/Images/right2.png";
        topBar=url+"/Pages/certificate/Consultant/Images/top.png";
        bottomBar=url+"/Pages/certificate/Consultant/Images/botom.png";
        architectLogo = url+"/Pages/certificate/Consultant/Images/logonew.png";
        seal = url+"/Pages/certificate/Consultant/Images/seal.png";
        background = url+"/Pages/certificate/Consultant/Images/backgroundLogo1.jpg";
        sign = url+"/Pages/certificate/Consultant/Images/sign.png";

        try{
                filepath = url+ ReportConstant.ARCHITECT_CERTIFICATE;
              //  query = "SELECT f.`CIDNo` FROM crparchitectfinal f  WHERE f.`ARNo`=? ";
            String servicefor="";
            if(applicationNo.contains("BA")){
                servicefor="Architect";
            }
            if(applicationNo.contains("BS")){
                servicefor="Surveyor";
            }
            System.out.println("inside the try:::"+applicationNo);
            parameters.put("applicationNo", applicationNo);
            parameters.put("leftSideBar", leftSideBar);
            parameters.put("rightSideBar", rightSideBar);
            parameters.put("topBar", topBar);
            parameters.put("bottomBar", bottomBar);

            parameters.put("seal", seal);
            parameters.put("background", background);
            parameters.put("sign", sign);
            parameters.put("architectLogo", architectLogo);
            parameters.put("serviceFor", servicefor);
           // connection = ConnectionManager.getConnection(ReportConstant.DATASOURCENAMECDBMASTER.getQuery());
           // connection.setAutoCommit(false);
            String cid="";

          /*  PreparedStatement pst_dcsidb = connection.prepareStatement(query);
            pst_dcsidb.setString(1, applicationNo);
            ResultSet rs_dcsidb = pst_dcsidb.executeQuery();
            while(rs_dcsidb.next()){
                if(rs_dcsidb.getString("CIDNo").length()==11){
                    cid=rs_dcsidb.getString("CIDNo");
                }
            }*/
            String imageURL = "http://www.citizenservices.gov.bt/BtImgWS/ImageServlet?type=PH&cidNo="+cid;
            System.out.println(imageURL);
            parameters.put("image", imageURL);
        //    JasperReport jasperreport = (JasperReport) JRLoader.loadObject(filepath);
            parameters.put(net.sf.jasperreports.engine.JRParameter.IS_IGNORE_PAGINATION,Boolean.FALSE);
         //   jasperprint = JasperFillManager.fillReport(jasperreport, parameters);
            System.out.println("after try");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return jasperprint;
    }
}
