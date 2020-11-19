package com.ngn.spring.project.cdb.specializedFirm.renewal;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.registration.ContractorNRService;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorDTO;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
import com.ngn.spring.project.cdb.contractor.renewal.RenewalServiceType;
import com.ngn.spring.project.cdb.specializedFirm.SpecializedFirmService;
import com.ngn.spring.project.cdb.specializedFirm.dto.SpFirmDTO;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ==================================================================================
 * Created by user on 3/12/2020.
 * Description: Contractor renewal of certificate controller --RC (Renewal of Certificate)
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/public_access/specializedFirmRC")
public class SpecializedFirmRController extends BaseController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private SpecializedFirmRService cRenewalService;
    @Autowired
    private SpecializedFirmService specializedFirmService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("res")ResponseMessage res) {
        if(res != null && res.getStatus() != null){
            model.addAttribute("res",res);
            return "specializedFirm/renewal/specializedFirmRC";
        }
        String cdbNo = request.getSession().getAttribute("CDBNo").toString();
        responseMessage = cRenewalService.check4Renewal(cdbNo.split("999")[1]);
        model.addAttribute("renewalCheck",responseMessage);

        if(responseMessage.getStatus() == 0){
            return "specializedFirm/renewal/specializedFirmRC";
        }

        String auditMemo = cRenewalService.auditMemo(responseMessage.getId());
        if(auditMemo != null && !auditMemo.isEmpty()){
            responseMessage.setStatus(0);
            responseMessage.setText(auditMemo);
            return "specializedFirm/renewal/specializedFirmRC";
        }

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
        model.addAttribute("feeStructureList", specializedFirmService.gFeeStructure(null));
        model.addAttribute("qualificationList", commonService.gCmnListItem(cmnQualification));
        model.addAttribute("serviceTypeList", commonService.gCmnListItem(cmnServiceType));
        model.addAttribute("equipmentList", specializedFirmService.gEquipment());
        model.addAttribute("tradeList", commonService.gCmnListItem(cmnTrade));
        //model.addAttribute("osFees", cRenewalService.getServicesFee(null));
        return "specializedFirm/renewal/specializedFirmRC";
    }

    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request,SpFirmDTO spFirmDTO, RenewalServiceType renewalService
            ,RedirectAttributes redirectAttributes) throws Exception{
        loggedInUser = gLoggedInUser(request);
        responseMessage =cRenewalService.save(spFirmDTO,renewalService,loggedInUser);
        if(responseMessage.getStatus() == 1){
            redirectAttributes.addFlashAttribute("acknowledgement_message",responseMessage.getText());
        } else{
            redirectAttributes.addFlashAttribute("res", responseMessage);
            return "redirect:/public_access/specializedFirmRC";
        }
        return "redirect:/acknowledgement";
    }

    @ResponseBody
    @RequestMapping(value ="/getSpFirm", method = RequestMethod.GET)
    public Object getSpecializedFirmFinal(HttpServletRequest request){
        String cdbNo = request.getSession().getAttribute("CDBNo").toString();
        return cRenewalService.getSpecializedFirmFinal(cdbNo.split("999")[1]);
    }

    @ResponseBody
    @RequestMapping(value ="/getSpFirmHRsFinal", method = RequestMethod.GET)
    public List getSpFirmHRsFinal(HttpServletRequest request, String specializedFirmId,Character ownerOrHR){
        return cRenewalService.getSpecializedFirmHRsFinal(specializedFirmId, ownerOrHR);
    }

    @ResponseBody
    @RequestMapping(value ="/getEquipmentFinal", method = RequestMethod.GET)
    public List getEquipmentFinal(HttpServletRequest request, String specializedFirmId){
        return cRenewalService.getEquipmentFinal(specializedFirmId);
    }

    @ResponseBody
    @RequestMapping(value ="/getCategory", method = RequestMethod.GET)
    public List getFeeCategoryClass(HttpServletRequest request, String specializedFirmId){
        return cRenewalService.getCategoryClassFinal(specializedFirmId);
    }

    @RequestMapping(value = "/viewDownload", method = RequestMethod.GET)
    public void viewDownload(HttpServletRequest request, HttpServletResponse response, String documentPath) throws Exception{
        commonService.viewDownloadFile(documentPath, response);
    }
}
