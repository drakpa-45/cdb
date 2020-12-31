package com.ngn.spring.project.cdb.specializedFirm;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmDTO;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ==================================================================================
 * Created by user on 9/29/2019.
 * Description:
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Controller
@RequestMapping("/specializedFirm")
public class SpecializedFirmController extends BaseController {
    @Autowired
    private SpecializedFirmService services;
    @Autowired
    private CommonService commonService;

	@RequestMapping(value ="", method = RequestMethod.GET)
	public String index(ModelMap model,HttpServletRequest request, HttpServletResponse response,String error) {
        String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
        String cmnOwnership = "08dada52-c651-11e4-b574-080027dcfac6";
        String trade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";
        String cmnDesignation = "599fbfdc-a250-11e4-b4d2-080027dcfac6";
        String cmnServiceType = "08dada52-c651-11e4-b574-080027dcfee6";
        String category="Specialized Trade Firm";

        model.addAttribute("feeStructureList", services.gFeeStructure(category));
        model.addAttribute("ownershipList", commonService.gCmnListItem(cmnOwnership));
        model.addAttribute("countryList", commonService.gCountryList());
        model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        model.addAttribute("tradeList", commonService.gCmnListItem(trade));
        model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
        model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));
        model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
        //model.addAttribute("undertaking", commonService.getundertaking("Architect_Registration"));
        model.addAttribute("categoryList", services.categoryFirm());
        model.addAttribute("designationList", commonService.gCmnListItem(cmnDesignation));
        model.addAttribute("equipmentList", services.gEquipment());
        model.addAttribute("serviceTypeList", commonService.gCmnListItem(cmnServiceType));
        model.addAttribute("error", error);

        return "specializedFirm/registration/specializedFirm";
	}


    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request,SpFirmDTO spFirmDTO,RedirectAttributes redirectAttributes) throws Exception{
        loggedInUser = gLoggedInUser(request);
        responseMessage = services.save(spFirmDTO,loggedInUser,request);
        if(responseMessage.getStatus() == 1){
            redirectAttributes.addFlashAttribute("acknowledgement_message",responseMessage.getText());
        }else{
            redirectAttributes.addFlashAttribute("error",responseMessage.getText());
            return "redirect:/specializedFirm";
        }
        return "redirect:/acknowledgement";
    }

    @ResponseBody
    @RequestMapping(value ="/getGewogList", method = RequestMethod.GET)
    public ResponseMessage getGewogList(String dzongkhagId){
        return commonService.getGewogList(dzongkhagId);
    }

    @ResponseBody
    @RequestMapping(value ="/getVillageList", method = RequestMethod.GET)
    public ResponseMessage getVillageList(String gewogId){
        return commonService.getVillageList(gewogId);
    }

    @ResponseBody
    @RequestMapping(value ="/getPersonalInfo", method = RequestMethod.GET)
    public ResponseMessage getPersonalInfo(String cidNo, String type){
        return commonService.getPersonalInfo(cidNo,type);
    }

    @ResponseBody
    @RequestMapping(value ="/isFirmNameUnique", method = RequestMethod.GET)
    public Boolean isFirmNameUnique(String firmName){
        return services.isFirmNameUnique(firmName);
    }

    @ResponseBody
    @RequestMapping(value ="/isEmailUnique", method = RequestMethod.GET)
    public Boolean isEmailUnique(String email){
        return services.isEmailUnique(email);
    }

    @ResponseBody
    @RequestMapping(value ="/getTrainingDtl", method = RequestMethod.GET)
    public List getTrainingDtl(String cidNo){
        return services.getTrainingDtl(cidNo);
    }

    @RequestMapping(value ={"/spfirmopenRejectedApplication"}, method = RequestMethod.GET)
    public String fetchRejectedAppDetails(HttpServletRequest request,String appNo,Model model,String error) {
        String serviceSectorType = "8d6e1df8-bea7-11e4-9757-080027dcfac6";
        String cmnOwnership = "08dada52-c651-11e4-b574-080027dcfac6";
        String trade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String qualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";
        String cmnDesignation = "599fbfdc-a250-11e4-b4d2-080027dcfac6";
        String cmnServiceType = "08dada52-c651-11e4-b574-080027dcfee6";
        String category="Specialized Trade Firm";

        model.addAttribute("feeStructureList", services.gFeeStructure(category));
        model.addAttribute("ownershipList", commonService.gCmnListItem(cmnOwnership));
        model.addAttribute("countryList", commonService.gCountryList());
        model.addAttribute("typeList", commonService.gCmnListItem(serviceSectorType));
        model.addAttribute("tradeList", commonService.gCmnListItem(trade));
        model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
        model.addAttribute("qualificationList", commonService.gCmnListItem(qualification));
        model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
        //model.addAttribute("undertaking", commonService.getundertaking("Architect_Registration"));
        model.addAttribute("categoryList", services.categoryFirm());
        model.addAttribute("designationList", commonService.gCmnListItem(cmnDesignation));
        model.addAttribute("equipmentList", services.gEquipment());
        model.addAttribute("serviceTypeList", commonService.gCmnListItem(cmnServiceType));
        model.addAttribute("error", error);

        return "specializedFirm/registration/specializedFirm";
    }

}