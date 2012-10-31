package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Offer;
import com.thoughtworks.twu.service.OfferServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        CreateOfferController createOfferController = new CreateOfferController(offerServiceInterface);
        String title = "this is a title";
        String category = "Cars";
        String description = "there is some descriptions";
        String owner = "Qiushi";

        ModelAndView modelAndView = createOfferController.createOffer(title, category, description, owner);
        String modelOfferId = (String) modelAndView.getModel().get("offerId");

        Offer offer = new Offer(modelOfferId, title, description, category, owner);

        Offer offerFromDatabase = offerServiceInterface.getOfferById(modelOfferId);

        assertThat(offerFromDatabase, is(offer));

    }
}
