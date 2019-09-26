package com.blansplatform.service;

import com.blansplatform.entity.Offer;
import com.blansplatform.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OfferService {

    final private OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    public Offer findOfferById(Long id) {
        Offer offer = offerRepository.findOfferById(id);
        if (offer == null) {
            throw new EntityNotFoundException("Offer not found");
        }
        return offer;
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
