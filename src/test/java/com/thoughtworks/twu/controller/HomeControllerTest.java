package com.thoughtworks.twu.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpSession;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HomeControllerTest {
    HomeController homeController;
    MockHttpServletRequest request;
    HttpSession session;

    @Before
    public void setUp() {
        homeController = new HomeController();
        request = new MockHttpServletRequest();
        session = request.getSession();
    }

    @Test
    public void shouldGoToHomePage() {
        assertThat(homeController.goToHomepage(request, session), is("home/home"));
    }

    @Test
    public void shouldSetUsernameCorrectly() {
        request.setRemoteUser("Fernando");
        homeController.goToHomepage(request, session);
        assertThat(session.getAttribute("username").toString(), is("Fernando"));
    }
}

