package com.thoughtworks.twu.service;

import com.thoughtworks.twu.domain.Offer;

public interface OfferServiceInterface {
    Offer getOfferById(String offerId);

    String saveOffer(Offer offer);
}
