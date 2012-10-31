package com.thoughtworks.twu.controller;

import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CreateOfferControllerTest {

    @Test
    public void shouldGoToCreateOffer(){
        CreateOfferController createOfferController = new CreateOfferController();
        String nextView = createOfferController.createOffer();
        String expectedView = "home/createOffer";

        assertThat(nextView, is(expectedView));
    }
}
