package com.thoughtworks.twu.persistence;

import com.thoughtworks.twu.domain.Offer;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Repository
public class OfferDao {

    @Autowired
    private SessionFactory sessionFactory;

    public String saveOffer(Offer offer) {
//        sessionFactory.openSession().save(offer);
        sessionFactory.getCurrentSession().save(offer);
        return offer.getId();
    }

    public Offer getOfferById(String offerId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Offer where id=?").setParameter(0, offerId);
        Offer offer = (Offer) query.list().get(0);
        return offer;
    }
}
