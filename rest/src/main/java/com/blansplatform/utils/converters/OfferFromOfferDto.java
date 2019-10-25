/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.OfferDto;
import com.blansplatform.entity.Offer;

public class OfferFromOfferDto {

    public static Offer offerConverter(OfferDto offerDto) {
        return new Offer(
                offerDto.getBid(),
                offerDto.getComment()
        );
    }

}
