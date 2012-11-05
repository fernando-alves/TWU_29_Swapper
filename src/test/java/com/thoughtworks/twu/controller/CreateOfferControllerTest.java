package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Offer;
import com.thoughtworks.twu.service.OfferService;
import com.thoughtworks.twu.service.OfferServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
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

    @Test
    public void shouldExposeAllOffersToBrowseView() throws Exception {

        OfferService offerService = mock(OfferService.class);
        CreateOfferController createOfferController = new CreateOfferController(offerService);

        List<Offer> expectedOffers = new ArrayList<Offer>();

        Offer firstOffer = new Offer("Title 1", "Category 1", "Description 1", "Me");
        Offer secondOffer = new Offer("Title 2", "Category 2", "Description 2", "You");
        Offer thirdOffer = new Offer("Title 3", "Category 3", "Description 3", "Someone else");

        expectedOffers.add(firstOffer);
        expectedOffers.add(secondOffer);
        expectedOffers.add(thirdOffer);

        when(offerService.getAll()).thenReturn(expectedOffers);
        ModelAndView modelAndView = createOfferController.browse();
        Map<String, Object> model = modelAndView.getModel();

        List<Offer> actualOffers = (List<Offer>) model.get("allOffers");

        assertThat(expectedOffers, is(actualOffers));
    }

    public OfferServiceInterface mockOfferService(){
        return mock(OfferService.class);
    }
}