package com.thoughtworks.twu.controller;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProfileControllerTest {
    @Test
    public void shouldGoToCreateProfilePage() {
        ProfileController profileController = new ProfileController();
        assertThat(profileController.homepage().getViewName(), is("jsp/createProfile"));
    }

    @Test
    public void shouldGoToHomePage() {
        ProfileController profileController = new ProfileController();
        assertThat(profileController.addProfile().getViewName(), is("jsp/home"));
    }
}

