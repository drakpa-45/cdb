package com.ngn.spring.project.base;


import com.ngn.spring.project.lib.LoggedInUser;
import com.ngn.spring.project.lib.ResponseMessage;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ====================================================================
 * Created by nimayoezer on 21-08-18.
 * Description:
 * ====================================================================
 * Modified by:
 * Modified date:
 * Purpose:
 * ====================================================================
 */
public class BaseController {
    protected LoggedInUser loggedInUser;
    protected ResponseMessage responseMessage;

    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public ResponseMessage getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setAutoGrowCollectionLimit(2000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        // true passed to CustomDateEditor constructor means convert empty String to null
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    protected LoggedInUser gLoggedInUser(HttpServletRequest request) {
        loggedInUser = (LoggedInUser) request.getSession().getAttribute("loggedInUser");
        String cdbNo = (String) request.getSession().getAttribute("CDBNo");
        if(loggedInUser == null){
           loggedInUser =new LoggedInUser();
            //loggedInUser.setUserID("10907001947");
            loggedInUser.setUserName("10907001947");
            loggedInUser.setServerDate(new Date());
        }
        loggedInUser.setCdbNo(cdbNo);
        return loggedInUser;
    }


    //TODO for System Date
    public String getDateFormat(Date date) {
        return new SimpleDateFormat("dd-MMM-yyyy").format(date);
    }

}
