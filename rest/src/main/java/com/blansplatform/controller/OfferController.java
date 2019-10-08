/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.controller;

import com.blansplatform.dto.OfferDto;
import com.blansplatform.entity.Offer;
import com.blansplatform.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {
    final private OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public List<OfferDto> findAllOffers() {
        return offerService.findAll();
    }

    @GetMapping(path = "/{id}")
    public OfferDto findOfferById(@PathVariable Long id) {
        return offerService.findOfferById(id);
    }

    @PostMapping
    public @ResponseBody Response addNewOffer(@RequestBody Offer offer) {
        offerService.addOffer(offer);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DeleteMapping
    public @ResponseBody Response deleteOffer(@RequestBody Offer offer) {
        offerService.deleteOffer(offer);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PutMapping
    public @ResponseBody Response updateOffer(@RequestBody Offer offer) {
        offerService.updateOffer(offer);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
