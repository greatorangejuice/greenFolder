/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.OfferDto;
import com.blansplatform.entity.Offer;
import com.blansplatform.entity.User;

public class OfferDtoFromOffer {

    public static OfferDto offerConverter(Offer offer) {
        return new OfferDto(
                offer.getId(),
                offer.getTask().getSecretId(),
                offer.getBid(),
                offer.getOfferStatus(),
                usernameNullPointerAvoid(offer.getExecutor()),
                usernameNullPointerAvoid(offer.getCustomer()),
                offer.getComment()
        );
    }

    private static String usernameNullPointerAvoid(User user) {
        if(user == null) {
            return "no user";
        }
        return user.getUsername();
    }

}
