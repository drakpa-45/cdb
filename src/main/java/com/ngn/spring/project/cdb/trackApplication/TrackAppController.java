package com.ngn.spring.project.cdb.trackApplication;

import com.ngn.spring.project.commonDto.TasklistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by USER on 18-Dec-20.
 */
@Controller
@RequestMapping("")
public class TrackAppController {
    @Autowired
    private TrackAppService trackAppService;

    @RequestMapping(value = "/trackApp")
    public String redirectToPage( ModelMap model,HttpServletRequest request,String applicationNo) {
        model.addAttribute("applicationHistory", trackAppService.populateTrackApp(applicationNo));
        return "trackApplication/trackApp";
    }
}
