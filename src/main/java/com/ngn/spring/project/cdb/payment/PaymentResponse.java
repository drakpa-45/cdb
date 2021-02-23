package com.ngn.spring.project.cdb.payment;

import com.ngn.spring.project.cdb.admin.dto.PaymentUpdateDTO;
import com.ngn.spring.project.cdb.admin.specializedFirm.SpecializedFirmActionService;
import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wso2.client.model.G2C_CommonBusinessAPI.UserRolePrivilegeHierarchyObj;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Deepak on 2/3/2021.
 */
@Controller
@RequestMapping("/PaymentResponse")
public class PaymentResponse extends HttpServlet {
    @Autowired
    private SpecializedFirmActionService specializedFirmActionService;

    @RequestMapping(method = RequestMethod.GET)
    public void onlinepaymentUpdate(HttpServletRequest request, LoggedInUser loggedInUser) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PaymentUpdateDTO paymentUpdateDTO = new PaymentUpdateDTO();

        try {
            String paymentDate = request.getParameter("paymentDate");
            Date date = new Date(paymentDate);
            paymentUpdateDTO.setAppNo(request.getParameter("applicationNo"));
            paymentUpdateDTO.setPaymentDate(date);
            paymentUpdateDTO.setPaymentReceiptNo(String.valueOf((request.getParameter("txnId"))));
            paymentUpdateDTO.setPaymentAmount(BigDecimal.valueOf(Long.parseLong(request.getParameter("txnAmount"))));

            String appNo = request.getParameter("applicationNo");
            String cdbNo = specializedFirmActionService.getNextCDBNo(appNo);
            paymentUpdateDTO.setCdbNo(cdbNo);
            specializedFirmActionService.onlinepaymentUpdate(paymentUpdateDTO, loggedInUser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
