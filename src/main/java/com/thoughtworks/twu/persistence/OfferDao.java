package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Offer;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OfferDao {

    @Autowired
    private SessionFactory sessionFactory;

    public String saveOffer(Offer offer) {
        sessionFactory.getCurrentSession().save(offer);
        return offer.getId();
    }

    public Offer getOfferById(String offerId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Offer where Id=?").setParameter(0, offerId);
        Offer offer = (Offer) query.uniqueResult();
        return offer;
    }

    public List<Offer> getAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Offer where Hidden = 'false'");

        return (List<Offer>) query.list();
    }

    public void takeDown(String offerId) {
        Offer offer = getOfferById(offerId);
        offer.setHidden(true);
        saveOffer(offer);
    }
}
