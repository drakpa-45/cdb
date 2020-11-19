package com.ngn.spring.project.cdb.contractor.os;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.registration.ContractorNRService;
import com.ngn.spring.project.cdb.contractor.registration.dto.ContractorDTO;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
import com.ngn.spring.project.cdb.contractor.renewal.RenewalServiceType;
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
 * Description: Contractor Other Services controller --OS (Other services)
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/public_access/contractorOS")
public class ContractorOSController extends BaseController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private ContractorOSService otherService;
    @Autowired
    private ContractorRCService cRenewalService;
    @Autowired
    private ContractorNRService contractorNRService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("res")ResponseMessage res) {
        if(res != null && res.getStatus() != null){
            model.addAttribute("res",res);
            return "contractor/os/contractorOS";
        }
        String cdbNo = request.getSession().getAttribute("CDBNo").toString();
        cdbNo = cdbNo.split("999")[1];

        responseMessage = cRenewalService.check4Renewal(cdbNo,false);
        model.addAttribute("renewalCheck",responseMessage);
        model.addAttribute("cdbNo", cdbNo);

        if(responseMessage.getStatus() == 0){
            return "contractor/os/contractorOS";
        }
        String auditMemo = cRenewalService.auditMemo(responseMessage.getId());
        model.addAttribute("auditMemo", auditMemo);
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
        //model.addAttribute("feeStructureList", contractorNRService.gFeeStructure(null));
        model.addAttribute("qualificationList", commonService.gCmnListItem(cmnQualification));
        model.addAttribute("serviceTypeList", commonService.gCmnListItem(cmnServiceType));
        model.addAttribute("equipmentList", contractorNRService.gEquipment());
        model.addAttribute("tradeList", commonService.gCmnListItem(cmnTrade));

        return "contractor/os/contractorOS";
    }

    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request,ContractorDTO contractorDTO, RenewalServiceType renewalService
            ,RedirectAttributes redirectAttributes) throws Exception{
        loggedInUser = gLoggedInUser(request);
        responseMessage =otherService.save(contractorDTO,renewalService,loggedInUser);
        if(responseMessage.getStatus() == 1){
            redirectAttributes.addFlashAttribute("acknowledgement_message",responseMessage.getText());
        } else{
            redirectAttributes.addFlashAttribute("res", responseMessage);
            return "redirect:/public_access/contractorOS";
        }
        return "redirect:/acknowledgement";
    }

    @ResponseBody
    @RequestMapping(value ="/getContractor", method = RequestMethod.GET)
    public Object getContractor(HttpServletRequest request){
        String cdbNo = request.getSession().getAttribute("CDBNo").toString();
        return cRenewalService.getContractorFinal(cdbNo.split("999")[1]);
    }

    @ResponseBody
    @RequestMapping(value ="/getContractorHRsFinal", method = RequestMethod.GET)
    public List getContractorHRsFinal(HttpServletRequest request, String contractorId,Character ownerOrHR){
        return cRenewalService.getContractorHRsFinal(contractorId, ownerOrHR);
    }

    @ResponseBody
    @RequestMapping(value ="/getEquipmentFinal", method = RequestMethod.GET)
    public List getEquipmentFinal(HttpServletRequest request, String contractorId){
        return cRenewalService.getEquipmentFinal(contractorId);
    }

    @ResponseBody
    @RequestMapping(value ="/getCategory", method = RequestMethod.GET)
    public List getFeeCategoryClass(HttpServletRequest request, String contractorId){
        return cRenewalService.getCategoryClassFinal(contractorId);
    }


    @RequestMapping(value = "/viewDownload", method = RequestMethod.GET)
    public void viewDownload(HttpServletRequest request, HttpServletResponse response, String documentPath) throws Exception{
        commonService.viewDownloadFile(documentPath, response);
    }

}
