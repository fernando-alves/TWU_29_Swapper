package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Offer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class OfferDaoTest {

    @Autowired
    private OfferDao offerDao;


    @Test
    public void shouldSaveAnimalOfferCorrectly(){
       String offerTitle = "My cat";
       String offerDescription = "Hey, feed my cat!";
       String offerCategory = "ANIMAL";
       String offerOwner = "Hermione";


       Offer offer = new Offer("12345", offerTitle, offerDescription, offerCategory, offerOwner);
       String generatedId = offerDao.saveOffer(offer);

       Offer offerFromDatabase = offerDao.getOfferById(generatedId);

       assertThat(offerFromDatabase, is(offer));


    }

    @Test
    public void shouldSaveHouseOffer(){
        String offerTitle = "House on England";
        String offerDescription = "Harrys house";
        String offerCategory = "HOUSE";
        String offerOwner = "Dumbledore";


        Offer offer = new Offer("12345", offerTitle, offerDescription, offerCategory, offerOwner);

        String generatedId = offerDao.saveOffer(offer);

        Offer offerFromDatabase = offerDao.getOfferById(generatedId);

        assertThat(offerFromDatabase, is(offer));


    }

}