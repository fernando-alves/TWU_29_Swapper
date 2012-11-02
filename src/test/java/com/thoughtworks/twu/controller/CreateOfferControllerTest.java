package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Offer;
import com.thoughtworks.twu.service.OfferService;
import com.thoughtworks.twu.service.OfferServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CreateOfferControllerTest {

    @Autowired
    OfferServiceInterface offerServiceInterface;

    @Test
    public void shouldGoToCreateOffer() {
        CreateOfferController createOfferController = new CreateOfferController(offerServiceInterface);
        String nextView = createOfferController.goToHomepageAfterCreatingOffer();
        String expectedView = "home/createOffer";

        assertThat(nextView, is(expectedView));
    }

    @Test
    public void shouldSaveOfferCorrectly() {

        OfferServiceInterface mockOfferService = mockOfferService();
        CreateOfferController createOfferController = new CreateOfferController(mockOfferService);

        String title = "this is a title";
        String category = "Cars";
        String description = "there is some descriptions";
        String owner = "Qiushi";

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("username")).thenReturn("Qiushi");

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setSession(session);

        Offer offer = new Offer(title, description, category, owner);

        createOfferController.createOffer(title, category, description, request);

        verify(mockOfferService).saveOffer(offer);
    }

    public OfferServiceInterface mockOfferService(){
        return mock(OfferService.class);
    }
}