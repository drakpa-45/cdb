package com.ngn.spring.project.cdb.printInfoCert;

import com.ngn.spring.project.cdb.common.CommonService;
import com.ngn.spring.project.cdb.profile.ProfileService;
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
 * Created by Pema Drakpa on 7/18/2020.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified on:
 * Changes made :
 * ====================================================================
 */
@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping(value ={"/public_access/printInfoCert"})
public class PrintInfoCertController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProfileService profileService;
    /**
     *
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value ="/printInformationContractor", method = RequestMethod.GET)
    public String printInformation(ModelMap model,HttpServletRequest request, HttpServletResponse response) {
        //String type=request.getParameter("param");
        String cdbNo=request.getParameter("cdbNo");
        model.addAttribute("appDetail", profileService.getApplicationDetails(cdbNo));
        return "contractor/printInformation/contractorInformation";
    }
}
