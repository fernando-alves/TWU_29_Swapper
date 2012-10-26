package com.thoughtworks.twu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
    @RequestMapping("/")
    public ModelAndView homepage() {
        return new ModelAndView("jsp/createProfile");
    }

    @RequestMapping(value = "home", method = RequestMethod.POST)
    public ModelAndView addProfile() {
        return new ModelAndView("jsp/home");
    }
}
