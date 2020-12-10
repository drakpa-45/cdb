package com.ngn.spring.project.cdb.profile;

import com.ngn.spring.project.cdb.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ====================================================================
 * Created by Nima Yoezer on 7/18/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value ={"/public_access/profile"})
public class ProfileController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProfileService profileService;

    @RequestMapping(value ="", method = RequestMethod.GET)
    public String profile(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        String type=request.getParameter("param");
        if(type == null){
            type = request.getSession().getAttribute("CDBNo").toString();
        }
        if(type.split("999")[0].equalsIgnoreCase("Architect")){
            model.addAttribute("registrationDetails", commonService.populateApplicantDetails(type.split("999")[1]));
            model.addAttribute("App_Details", commonService.populateApplicantDetails(type.split("999")[1]));
            return "architect/architect_profile";
        }
        if(type.split("999")[0].equalsIgnoreCase("SpecializedTrade")){
            model.addAttribute("registrationDetails", commonService.populateSpApplicantDetails(type.split("999")[1]));
            model.addAttribute("App_Details", commonService.populateSpApplicantDetails(type.split("999")[1]));
            return "trade/spTrade_profile";
        }
        if(type.split("999")[0].equalsIgnoreCase("Survey")){
            model.addAttribute("registrationDetails", commonService.populateSurveyApplicantDetails(type.split("999")[1]));
            model.addAttribute("App_Details", commonService.populateSurveyApplicantDetails(type.split("999")[1]));
            return "survey/survey_profile";
        }
        if(type.split("999")[0].equalsIgnoreCase("Consultant")){
            model.addAttribute("appDetail", profileService.getApplicationDetailsConsultant(type.split("999")[1]));;
            return "consultant/profile/consultant_profile";
        }
        if(type.split("999")[0].equalsIgnoreCase("Contractor")){
            model.addAttribute("appDetail", profileService.getApplicationDetails(type.split("999")[1]));
            return "contractor/profile/contractor_profile";
        }
        if(type.split("999")[0].equalsIgnoreCase("SpecializedFirm")){
            model.addAttribute("appDetail", profileService.getApplicationDetailsSpecializedFirm(type.split("999")[1]));;
            return "specializedFirm/profile/specializedFirm_profile";
        }
        if(type.split("999")[0].equalsIgnoreCase("Engineer")){
            model.addAttribute("registrationDetails", commonService.populateEngineerApplicantDetails(type.split("999")[1]));
            model.addAttribute("App_Details", commonService.populateEngineerApplicantDetails(type.split("999")[1]));
            return "engineer/engineer_profile";
        }
        return null;
    }

    @RequestMapping(value ="/printInformation", method = RequestMethod.GET)
    public String printInformation(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        //String type=request.getParameter("param");
        String cdbNo=request.getParameter("cdbNo");
        if(cdbNo.startsWith("SP-")){
            model.addAttribute("registrationDetails", commonService.populateSpApplicantDetails(cdbNo));
            model.addAttribute("App_Details", commonService.populateSpApplicantDetails(cdbNo));
            return "trade/spTrade_printInformation";
        }
        return null;
    }
}
