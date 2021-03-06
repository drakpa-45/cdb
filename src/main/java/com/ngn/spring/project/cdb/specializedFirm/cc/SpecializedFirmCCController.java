package com.ngn.spring.project.cdb.specializedFirm.cc;

import com.ngn.spring.project.base.BaseController;
import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.contractor.cc.CancellationDTO;
import com.ngn.spring.project.cdb.contractor.cc.ContractorCCService;
import com.ngn.spring.project.cdb.contractor.renewal.ContractorRCService;
import com.ngn.spring.project.cdb.specializedFirm.renewal.SpecializedFirmRService;
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

/**
 * ==================================================================================
 * Created by user on 3/12/2020.
 * Description: Cancellation of certificate controller --CC (Cancellation of Certificate)
 * Modified by:
 * Reason :
 * ==================================================================================
 */

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/public_access/specializedFirmCC")
public class SpecializedFirmCCController extends BaseController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private SpecializedFirmRService cRenewalService;
    @Autowired
    private SpecializedFirmCCService specializedFirmCCService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(ModelMap model, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("res")ResponseMessage res) {
        if(res != null && res.getStatus() != null){
            model.addAttribute("res",res);
            return "specializedFirm/cc/specializedFirmCC";
        }
        String cdbNo = request.getSession().getAttribute("CDBNo").toString();
        responseMessage = specializedFirmCCService.check4Renewal(cdbNo.split("999")[1]);
        model.addAttribute("renewalCheck",responseMessage);
        model.addAttribute("cdbNo", cdbNo.split("999")[1]);

        return "specializedFirm/cc/specializedFirmCC";
    }

    @ResponseBody
    @RequestMapping(value ="/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request,CancellationDTO cancellationDTO,RedirectAttributes redirectAttributes) throws Exception{
        loggedInUser = gLoggedInUser(request);

        responseMessage = specializedFirmCCService.save(cancellationDTO,loggedInUser);
        if(responseMessage.getStatus() == 1){
            redirectAttributes.addFlashAttribute("acknowledgement_message",responseMessage.getText());
        } else{
            redirectAttributes.addFlashAttribute("res", responseMessage);
            return "redirect:/public_access/specializedFirmCC";
        }
        return "redirect:/acknowledgement";
    }

    /*@ResponseBody
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
    }*/

}
