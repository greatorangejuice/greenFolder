/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.AcceptOrDeclineOfferDto;
import com.blansplatform.dto.OfferDto;
import com.blansplatform.entity.Offer;

import javax.ws.rs.core.Response;
import java.util.List;

public interface OfferService {

    List<OfferDto> findAll();

    OfferDto findOfferById(Long id);

    void addOffer(OfferDto offerDto);

    void deleteOffer(Offer offer);

    void deleteOfferById(Long id);

    void updateOffer(OfferDto offerDto);

    Response acceptOrDecline(AcceptOrDeclineOfferDto acceptOrDeclineOfferDto);

    List<OfferDto> geExecutorsActiveOffers(String username);

}
