package com.blansplatform.repository;

import com.blansplatform.entity.Offer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferRepository extends CrudRepository<Offer, Long> {
    List<Offer> findAll();
    Offer findOfferById(Long id);
}
