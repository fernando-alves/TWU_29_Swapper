package com.thoughtworks.twu.controller;

import com.thoughtworks.twu.domain.Offer;
import com.thoughtworks.twu.service.OfferService;
import com.thoughtworks.twu.service.OfferServiceInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class OfferControllerTest {

    @Autowired
    OfferServiceInterface offerServiceInterface;

    private OfferServiceInterface mockOfferService;
    private OfferController offerController;
    private String title;
    private String category;
    private String description;
    private String owner;
    private MockHttpServletRequest request;
    private HttpSession session;
    private HomeController homeController;
    private Date creationTime;

    @Before
    public void setUp() {
        mockOfferService = mock(OfferService.class);
        offerController = new OfferController(mockOfferService);
        title = "this is a title";
        category = "Cars";
        description = "there is some descriptions";
        owner = "Qiushi";
        homeController = new HomeController();
        request = new MockHttpServletRequest();
        session = request.getSession();
        creationTime = new Date();
    }

    @Test
    public void shouldGoToCreateOfferPage() {
        String actualUrl = offerController.goToCreatingOfferPage();
        String expectedUrl = "home/createOffer";

        assertThat(expectedUrl, is(actualUrl));
    }

    @Test
    public void shouldSaveOfferCorrectly() {
        createAnOffer();
        Offer offer = new Offer(title, description, category, owner, creationTime);

        verify(mockOfferService).saveOffer(offer);
    }

    @Test
    public void shouldReturnOfferPageAfterCreatingOffer() {
        createAnOffer();
        String actualView = offerController.viewAnOfferAfterCreating(new ModelMap());
        String expectedView = "home/viewAnOffer";

        assertThat(expectedView, is(actualView));
    }

    @Test
    public void shouldReturnTheCorrectUrlToDisplayOfferFromBrowse() {
        createAnOffer();
        String actualUrl = offerController.viewAnOfferFromBrowse(new ModelMap(), "");
        String expectedUrl = "home/viewAnOffer";

        assertThat(expectedUrl, is(actualUrl));
    }

    @Test
    public void shouldReturnTheCorrectAttributeFromModelMapAfterCreatingOffer() {
        createAnOffer();
        ModelMap modelMap = new ModelMap();
        offerController.viewAnOfferAfterCreating(modelMap);

        assertTrue(modelMap.containsAttribute("theOffer"));
    }

    @Test
    public void shouldReturnTheCorrectAttributeFromModelMapFromBrowse() {
        createAnOffer();
        ModelMap modelMap = new ModelMap();
        offerController.viewAnOfferAfterCreating(modelMap);

        assertTrue(modelMap.containsAttribute("theOffer"));
    }

    @Test
    public void shouldCallOfferServiceForBrowseViewPage() throws Exception {

        OfferService offerService = mock(OfferService.class);
        OfferController offerController = new OfferController(offerService);

        List<Offer> expectedOffers = new ArrayList<Offer>();

        Offer firstOffer = new Offer("Title 1", "Category 1", "Description 1", "Me", new Date());
        Offer secondOffer = new Offer("Title 2", "Category 2", "Description 2", "You", new Date());
        Offer thirdOffer = new Offer("Title 3", "Category 3", "Description 3", "Someone else", new Date());

        expectedOffers.add(thirdOffer);
        expectedOffers.add(secondOffer);
        expectedOffers.add(firstOffer);

        when(offerService.getAll()).thenReturn(expectedOffers);
        ModelAndView modelAndView = offerController.browse(request, session);
        Map<String, Object> model = modelAndView.getModel();

        List<Offer> actualOffers = (List<Offer>) model.get("allOffers");

        assertThat(expectedOffers, is(actualOffers));
    }

    @Test
    public void shouldSetUsernameCorrectly() {
        request.setRemoteUser("Fernando");
        homeController.goToHomepage(request, session);
        assertThat(session.getAttribute("username").toString(), is("Fernando"));
    }

    private void createAnOffer() {
        HttpSession session1 = mock(HttpSession.class);
        when(session1.getAttribute("username")).thenReturn("Qiushi");
        MockHttpServletRequest request1 = new MockHttpServletRequest();
        request1.setSession(session1);

        offerController.createOffer(title, category, description, request1, creationTime);
    }



}