package com.ngn.spring.project.cdb.consultant.registration;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.consultant.registration.dto.ConsultantDTO;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/consultantNR")
public class ConsultantNRController extends BaseController {
    @Autowired
    private ConsultantNRService consultantService;
    @Autowired
    private CommonService commonService;

	@RequestMapping(value ="", method = RequestMethod.GET)
	public String index(ModelMap model,HttpServletRequest request, HttpServletResponse response,String error) {
        String cmnOwnership = "08dada52-c651-11e4-b574-080027dcfac6";
        String cmnSalutation = "f237fdb8-a5ef-11e4-8ab5-080027dcfac6";
        String cmnDesignation = "599fbfdc-a250-11e4-b4d2-080027dcfac6";
        String cmnQualification = "ff4e55ee-a254-11e4-b4d2-080027dcfac6";
        String cmnTrade = "bf4b32e8-a256-11e4-b4d2-080027dcfac6";
        String cmnServiceType = "08dada52-c651-11e4-b574-080027dcfee6";
		model.addAttribute("countryList", commonService.gCountryList());
		model.addAttribute("ownershipList", commonService.gCmnListItem(cmnOwnership));
		model.addAttribute("salutationList", commonService.gCmnListItem(cmnSalutation));
		model.addAttribute("designationList", commonService.gCmnListItem(cmnDesignation));
		model.addAttribute("dzongkhagList", commonService.gDzongkhagList());
		model.addAttribute("feeStructureList", consultantService.gFeeStructure("consultant"));
		model.addAttribute("categoryList", consultantService.gConsultantCategory());
		model.addAttribute("classification", consultantService.gClassification());
		model.addAttribute("qualificationList", commonService.gCmnListItem(cmnQualification));
		model.addAttribute("tradeList", commonService.gCmnListItem(cmnTrade));
		model.addAttribute("serviceTypeList", commonService.gCmnListItem(cmnServiceType));
		model.addAttribute("equipmentList", consultantService.gEquipment());
		model.addAttribute("error", error);

        return "consultant/registration/consultantNR";
	}

    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request,ConsultantDTO consultantDTO,RedirectAttributes redirectAttributes) throws Exception{
        loggedInUser = gLoggedInUser(request);
        responseMessage = consultantService.save(consultantDTO,loggedInUser,request);
        if(responseMessage.getStatus() == 1){
            redirectAttributes.addFlashAttribute("acknowledgement_message",responseMessage.getText());
        } else {
            redirectAttributes.addFlashAttribute("error",responseMessage.getText());
            return "redirect:/consultantNR";
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
        return commonService.getPersonalInfo(cidNo, type);
    }

    @ResponseBody
    @RequestMapping(value ="/isFirmNameUnique", method = RequestMethod.GET)
    public Boolean isFirmNameUnique(String firmName){
        return consultantService.isFirmNameUnique(firmName);
    }

    @ResponseBody
    @RequestMapping(value ="/isEmailUnique", method = RequestMethod.GET)
    public Boolean isEmailUnique(String email){
        return consultantService.isEmailUnique(email);
    }

    @ResponseBody
    @RequestMapping(value ="/getTrainingDtl", method = RequestMethod.GET)
    public List getTrainingDtl(String cidNo){
        return consultantService.getTrainingDtl(cidNo);
    }
}