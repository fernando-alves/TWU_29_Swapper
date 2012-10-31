package com.thoughtworks.twu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    // first time with ticket should use GET
    @RequestMapping(value = "/home.ftl", method = RequestMethod.POST)
    public String goToHomepageAfterCreatingOffer() {
        return "home/home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToHomepage(HttpServletRequest request, HttpSession session) {
        session.setAttribute("username", request.getRemoteUser());
        return "home/home";
    }
}
