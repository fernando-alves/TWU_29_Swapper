package com.thoughtworks.twu.service;


import com.thoughtworks.twu.domain.Offer;
import com.thoughtworks.twu.persistence.OfferDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class OfferServiceTest {


    @InjectMocks private OfferService offerService;
    @Mock private OfferDao offerDao;

    @Test
    public void shouldReturnOffersInReverseChronologicalOrder(){

        List<Offer> expectedResult = new ArrayList<Offer>();
        List<Offer> inputResult = new ArrayList<Offer>();

        Offer firstOffer = new Offer("Title 1", "Category 1", "Description 1", "Me", new Date());
        Offer secondOffer = new Offer("Title 2", "Category 2", "Description 2", "You", new Date());
        Offer thirdOffer = new Offer("Title 3", "Category 3", "Description 3", "Someone else", new Date());
        inputResult.add(firstOffer);
        inputResult.add(secondOffer);
        inputResult.add(thirdOffer);

        expectedResult.add(thirdOffer);
        expectedResult.add(secondOffer);
        expectedResult.add(firstOffer);

        when(offerDao.getAll()).thenReturn(inputResult);


        assertThat(expectedResult,is(offerService.getAll()));

    }


}
