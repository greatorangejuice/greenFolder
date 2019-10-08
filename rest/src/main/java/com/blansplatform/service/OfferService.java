/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.OfferDto;
import com.blansplatform.entity.Offer;
import com.blansplatform.repository.OfferRepository;
import com.blansplatform.utils.converters.OfferDtoFromOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    final private OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    public List<OfferDto> findAll() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream()
                .map(OfferDtoFromOffer::offerConverter)
                .collect(Collectors.toList());
    }

    public OfferDto findOfferById(Long id) {
        Offer offer = offerRepository.findOfferById(id);
        if (offer == null) {
            throw new EntityNotFoundException("Offer not found");
        }
        return OfferDtoFromOffer.offerConverter(offer);
    }

    public void addOffer(Offer offer) {
        offerRepository.save(offer);
    }

    public void deleteOffer(Offer offer) {
        offerRepository.delete(offer);
    }

    public void updateOffer(Offer offer) {
        offerRepository.save(offer);
    }
}
