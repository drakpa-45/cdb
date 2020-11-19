package com.ngn.spring.project.auth;

import com.ngn.spring.project.commonDto.TasklistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ==========================================================================================
 * Created by nimayoezer
 * Created date: 15-Jan-19
 * Description:
 * Purpose:
 * ==========================================================================================
 */
@Controller
@RequestMapping(value = "")
public class LoginController {
    /**
     * login loader
     *
     * @param error   error
     * @param request request
     * @return ModelAndView
     */
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        HttpServletRequest request, Model model) {

        if (error != null) {
            model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            model.addAttribute("orgName", "Construction Development Board");
          //  return "login";
            return "index";
        } else {
            return "redirect:/admin";
        }
    }

    /**
     * logout loader
     *
     * @return ModelAndView
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, auth);
       return "redirect:/login";
    }

    /**
     * authentication processing path
     *
     * @return ModelAndView
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ModelAndView auth() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    /**
     * access denied path
     *
     * @param request request
     * @return ModelAndView
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView model = new ModelAndView();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            LoginDTO userLogin = (LoginDTO) auth.getPrincipal();
            model.addObject("username", userLogin.getUsername());
        }
        model.setViewName("auth/403");
        return model;
    }

    @RequestMapping(value ={"/forgetPassword"}, method = RequestMethod.GET)
    public String ForgotPassword( ModelMap model,HttpServletRequest request,LoginDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView();
          LoginDTO userLogin = (LoginDTO) auth.getPrincipal();
        String username = userLogin.getUsername();
        loginService.checkForUserName(username);
        return "resubmissionIndex";
    }

    /**
     * to generate authentication error message
     *
     * @param request request
     * @param key     key
     * @return String
     */
    private String getErrorMessage(HttpServletRequest request, String key) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        if (exception != null) {
            return exception.getMessage();
        } else {
            return null;
        }
    }
}
