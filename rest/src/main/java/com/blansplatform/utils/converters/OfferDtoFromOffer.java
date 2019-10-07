/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.OfferDto;
import com.blansplatform.entity.Offer;

public class OfferDtoFromOffer {

    public static OfferDto offerConverter(Offer offer) {
        return new OfferDto(
                offer.getId(),
                offer.getBid(),
                offer.getOfferStatus()
        );
    }

}
