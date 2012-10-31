package com.thoughtworks.twu.controller;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LoginControllerTest {
    @Test
    public void shouldGoToHomePage() {
        LoginController loginController = new LoginController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        ModelMap modelMap = new ModelMap();

        assertThat(loginController.homepage(request,modelMap).getViewName(), is("example/home"));
    }

//    @Test
//    public void shouldGoToHomePage() {
//        ProfileController profileController = new ProfileController();
//
//        assertThat(profileController.addProfile().getViewName(), is("jsp/home"));
//    }
}

