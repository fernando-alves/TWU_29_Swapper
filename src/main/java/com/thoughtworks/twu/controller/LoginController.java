package com.thoughtworks.twu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @RequestMapping("/")
    public ModelAndView homepage(HttpServletRequest request,ModelMap modelMap) {
        System.out.println("UserName = " + request.getRemoteUser());
        modelMap.addAttribute("username",request.getRemoteUser());
        return new ModelAndView("example/home");
    }

//    @RequestMapping(value = "home", method = RequestMethod.POST)
//    public ModelAndView addProfile() {
//        return new ModelAndView("jsp/home");
//    }
}
