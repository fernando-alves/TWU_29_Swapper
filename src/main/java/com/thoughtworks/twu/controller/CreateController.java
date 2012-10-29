package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreateController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "?ticket", method = RequestMethod.GET)
    public ModelAndView homepage() {
        return new ModelAndView("html/home");
    }
}
