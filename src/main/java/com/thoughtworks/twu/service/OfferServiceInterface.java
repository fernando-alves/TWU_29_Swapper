package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Offer;

import java.util.List;

public interface OfferServiceInterface {
    Offer getOfferById(String offerId);
    String saveOffer(Offer offer);
    List<Offer> getAll();
}
