package com.thoughtworks.twu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateOfferController {
    @RequestMapping(value = "/createOffer.ftl", method = RequestMethod.GET)
    public String createOffer() {
        return "home/createOffer";
    }
}
