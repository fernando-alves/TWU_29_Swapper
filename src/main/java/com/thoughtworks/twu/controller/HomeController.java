package com.thoughtworks.twu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String goToHomepage(HttpServletRequest request, HttpSession session) {
        session.setAttribute("username", request.getRemoteUser());
        return "home/home";
    }
}
