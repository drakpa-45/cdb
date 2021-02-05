package com.ngn.spring.project.home;

import com.ngn.spring.project.auth.LoginDTO;
import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.commonDto.TasklistDto;
import com.ngn.spring.project.global.enu.ApplicationStatus;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.Date;

@Controller
@RequestMapping("")
public class IndexController extends BaseController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(value ="", method = RequestMethod.GET)
    public String index(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

    @PreAuthorize("isAuthenticated()")
	@RequestMapping(value ={"/admin"}, method = RequestMethod.GET)
	public String index_admin(ModelMap model,HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();

        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setUserID(loginDTO.getUserId());
        loggedInUser.setUserName(loginDTO.getUsername());
        loggedInUser.setFullName(loginDTO.getFullName());
        loggedInUser.setProfileId(loginDTO.getProfileId());
        loggedInUser.setServerDate(new Date());
        String registrationtype="";

        if(request.isUserInRole("ROLE_APPROVER")) {
            registrationtype= ApplicationStatus.VERIFIED.getCode();
        }else if(request.isUserInRole("ROLE_VERIFIER")){
            registrationtype= ApplicationStatus.UNDER_PROCESS.getCode();
        }else if(request.isUserInRole("ROLE_PAYMENT")){
            registrationtype= ApplicationStatus.APPROVED_FOR_PAYMENT.getCode();
        }
       request.setAttribute("architect_Count", commonService.populateCount("crparchitect", registrationtype));
       request.setAttribute("sptrade_Count", commonService.populateCount("crpspecializedTrade", registrationtype));
       request.setAttribute("consultant_Count", commonService.populateCount("crpconsultant", registrationtype));
       request.setAttribute("surveyor_Count", commonService.populateCount("crpsurvey", registrationtype));
       request.setAttribute("contractor_Count", commonService.populateCount("crpcontractor", registrationtype));
        request.getSession().setAttribute("loggedInUser", loggedInUser);

        if(request.isUserInRole("ROLE_PUBLIC")) {
            return "redirect:/public_access";
        }
        return "index_admin";
	}

    @PreAuthorize("isAuthenticated()")
	@RequestMapping(value ={"/public_access"}, method = RequestMethod.GET)
	public String index_public(ModelMap model,HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String cdbdet="";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();

        String getCdbNoForSp = commonService.getCdbNoForSp(loginDTO);
        String getCdbNoForSurvey = commonService.getCdbNoForSurvey(loginDTO);
        String getCdbNoForArchitect = commonService.getCdbNoForArchitect(loginDTO);
        String getCdbNoForEngineer= commonService.getCdbNoForEngineer(loginDTO);
        String getConsultantCdbNo = commonService.getConsultantCdbNo(loginDTO);
        String getCdbNoForContractor = commonService.getCdbNoForContractor(loginDTO);

        model.addAttribute("newsAndNotifications", commonService.getdashboardDetails(cdbdet.split("999")[0]));
       // model.addAttribute("rejectedApplications", commonService.populaterejectedApplications(cdbdet,getCdbNoForSp,getCdbNoForSurvey));
        session.setAttribute("loginDetails", loginDTO);

        if(getCdbNoForSp != null){
            if(getCdbNoForSp.contains("SP-")) {
                cdbdet=commonService.getSpCdbNo(loginDTO);
                session.setAttribute("App_Details", cdbdet);
                session.setAttribute("isExpired", commonService.isExpiredApplication(cdbdet));
                model.addAttribute("registrationDetails", commonService.populateSpApplicantDetails(cdbdet.split("999")[1]));
                model.addAttribute("applicationHistory", commonService.populateapplicationHistorySptrade(cdbdet.split("999")[1]));
                model.addAttribute("rejectedApplications", commonService.populaterejectedApplicationSptrade(cdbdet.split("999")[1]));
                return "index_public_spTrade";
            }else{
                cdbdet=commonService.getCdbNo(loginDTO);
                session.setAttribute("isExpired", commonService.isExpiredApplication(cdbdet));
                model.addAttribute("applicationHistory", commonService.populateapplicationHistorySpecializedFirm(cdbdet.split("999")[1]));
                model.addAttribute("rejectedApplications", commonService.populaterejectedApplicationSpecializedFirm(cdbdet.split("999")[1]));
                session.setAttribute("App_Details", cdbdet);
                session.setAttribute("CDBNo", cdbdet);
                return "index_public_specializedFirm";
            }
        } else if(getCdbNoForSurvey != null) {
            cdbdet=commonService.getSurveyCdbNo(loginDTO);
            session.setAttribute("App_Details", cdbdet);
            session.setAttribute("isExpired", commonService.isExpiredApplication(cdbdet));
            model.addAttribute("registrationDetails", commonService.populateSurveyApplicantDetails(cdbdet.split("999")[1]));
            model.addAttribute("applicationHistory", commonService.populateapplicationHistorySurvey(cdbdet.split("999")[1]));
            model.addAttribute("rejectedApplications", commonService.populaterejectedApplicationSurvey(cdbdet.split("999")[1]));
            return "index_public_survey";
        } else if(getCdbNoForArchitect != null) {
            cdbdet=commonService.getArchitectCDBNo(loginDTO);
            session.setAttribute("App_Details", cdbdet);
            session.setAttribute("isExpired", commonService.isExpiredApplication(cdbdet));
         //  model.addAttribute("registrationDetails", commonService.populateArchitectApplicantDetails(cdbdet.split("999")[1]));
            model.addAttribute("applicationHistory", commonService.populateapplicationHistoryArchitect(cdbdet.split("999")[1]));
            model.addAttribute("rejectedApplications", commonService.populaterejectedApplicationArchitect(cdbdet.split("999")[1]));
            return "index_public_architect";
        }else if(getCdbNoForEngineer != null) {
            cdbdet=commonService.getEngineerCDBNo(loginDTO);
            session.setAttribute("App_Details", cdbdet);
            session.setAttribute("isExpired", commonService.isExpiredApplication(cdbdet));
            //  model.addAttribute("registrationDetails", commonService.populateArchitectApplicantDetails(cdbdet.split("999")[1]));
            model.addAttribute("applicationHistory", commonService.populateapplicationHistoryEngineer(cdbdet.split("999")[1]));
            model.addAttribute("rejectedApplications", commonService.populaterejectedApplicationEngineer(cdbdet.split("999")[1]));
            return "index_public_engineer";
        }else if(getConsultantCdbNo!=null){
            cdbdet=commonService.getCdbNo(loginDTO);
            session.setAttribute("isExpired", commonService.isExpiredApplication(cdbdet));
            model.addAttribute("applicationHistory", commonService.populateapplicationHistoryConsultant(cdbdet.split("999")[1]));
            model.addAttribute("rejectedApplications", commonService.populaterejectedApplicationConsultant(cdbdet.split("999")[1]));
            session.setAttribute("App_Details", cdbdet);
            session.setAttribute("CDBNo", cdbdet);
            return "index_public_consultant";
        }else if(getCdbNoForContractor!=null){
            cdbdet=commonService.getCdbNo(loginDTO);
            session.setAttribute("isExpired", commonService.isExpiredApplication(cdbdet));
            model.addAttribute("applicationHistory", commonService.populateapplicationHistoryContractor(cdbdet.split("999")[1]));
            model.addAttribute("rejectedApplications", commonService.populaterejectedApplicationContractor(cdbdet.split("999")[1]));
            session.setAttribute("App_Details", cdbdet);
            session.setAttribute("CDBNo", cdbdet);
            return "index_public_contractor";
        }else{
            cdbdet=commonService.getCdbNo(loginDTO);
            session.setAttribute("App_Details", cdbdet);
            session.setAttribute("CDBNo", cdbdet);
           session.setAttribute("isExpired", commonService.isExpiredApplication(cdbdet));
            //model.addAttribute("registrationDetails", commonService.populateApplicantDetails(cdbdet.split("999")[1]));
            return "index_public";
        }
	}

    @RequestMapping(value = "/resubmissionIndex")
    public String redirectToPage( ModelMap model,HttpServletRequest request,TasklistDto dto) {
        return "resubmissionIndex";
    }

    @RequestMapping(value = "/acknowledgement")
    public String acknowledgement( ModelMap model,HttpServletRequest request,@ModelAttribute("acknowledgement_message")String acknowledgement_message) {
        model.addAttribute("ackMessage",acknowledgement_message);
        return "acknowledgement";
    }

    @ResponseBody
    @RequestMapping(value ="/public_access/isUsenameExist", method = RequestMethod.GET)
    public Boolean isUsenameExist(String username){
        return  commonService.isUsenameExist(username);
    }

    @ResponseBody
    @RequestMapping(value ="/public_access/updatePhoneNumber", method = RequestMethod.GET)
    public ResponseMessage updatePhoneNumber(String phoneNumber){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();
        return commonService.updatePhoneNumber(loginDTO,phoneNumber);
    }

    @ResponseBody
    @RequestMapping(value = "/public_access/updatePassword")
    public ResponseMessage updatePassword( ModelMap model,String username,String newPwd) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginDTO loginDTO = (LoginDTO) auth.getPrincipal();
        return commonService.updatePassword(loginDTO, username,newPwd);
    }
}