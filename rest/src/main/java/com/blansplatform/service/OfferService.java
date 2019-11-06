/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.AcceptOrDeclineOfferDto;
import com.blansplatform.dto.OfferDto;
import com.blansplatform.entity.Offer;
import com.blansplatform.entity.Task;
import com.blansplatform.entity.User;
import com.blansplatform.enumeration.OfferStatus;
import com.blansplatform.enumeration.TaskStatus;
import com.blansplatform.repository.OfferRepository;
import com.blansplatform.repository.TaskRepository;
import com.blansplatform.repository.UserRepository;
import com.blansplatform.utils.converters.OfferDtoFromOffer;
import com.blansplatform.utils.converters.OfferFromOfferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final UserService userService;
    private static final String POSITIVE_ANSWER_FOR_OFFER_FROM_CUSTOMER = "accept";
    private static final String NEGATIVE_ANSWER_FOR_OFFER_FROM_CUSTOMER = "decline";

    @Autowired
    public OfferService(OfferRepository offerRepository, UserRepository userRepository, TaskRepository taskRepository, UserService userService) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.userService = userService;
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

    public void addOffer(OfferDto offerDto) {
        Offer offer = OfferFromOfferDto.offerConverter(offerDto);
        offer.setTask(taskRepository.findTaskBySecretId(offerDto.getSecretId()));
        offer.setOfferStatus(OfferStatus.PROCESSING);
        offer.setCustomer(taskRepository.findTaskBySecretId(offerDto.getSecretId()).getCustomer());
        offer.setExecutor(userRepository.findFirstUserByUsername(offerDto.getExecutor()));
        offerRepository.save(offer);
    }

    public void deleteOffer(Offer offer) {
        offerRepository.delete(offer);
    }

    public void deleteOfferById(Long id) {
        Offer offerFromDb = OfferFromOfferDto.offerConverter(findOfferById(id));
        deleteOffer(offerFromDb);
    }

    public void updateOffer(OfferDto offerDto) {
        Offer offer = OfferFromOfferDto.offerConverter(offerDto);
        offerRepository.save(offer);
    }

    public Response acceptOrDecline(AcceptOrDeclineOfferDto acceptOrDeclineOfferDto) {
        Offer offer = offerRepository.findOfferById(acceptOrDeclineOfferDto.getOfferId());
        if (offer == null) {
            throw new EntityNotFoundException("offer not found");
        }
        if (acceptOrDeclineOfferDto.getCustomerResponse().equals(POSITIVE_ANSWER_FOR_OFFER_FROM_CUSTOMER)){
            offer.setOfferStatus(OfferStatus.APPROVED);
            Task taskFromDb = taskRepository.findTaskBySecretId(acceptOrDeclineOfferDto.getTaskSecretId());
            taskFromDb.setExecutor(userRepository.findFirstUserByUsername(acceptOrDeclineOfferDto.getExecutor()));
            taskFromDb.setTaskStatus(TaskStatus.INPROGRESS);
            taskRepository.save(taskFromDb);
            offerRepository.save(offer);
            declineOtherOffersForTaskIfOneApproved(acceptOrDeclineOfferDto.getTaskSecretId());
            return Response.status(Response.Status.OK).build();
        }
        if (acceptOrDeclineOfferDto.getCustomerResponse().equals(NEGATIVE_ANSWER_FOR_OFFER_FROM_CUSTOMER)) {
            offer.setOfferStatus(OfferStatus.REJECTED);
            offerRepository.save(offer);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.CONFLICT).build();
    }

    private void declineOtherOffersForTaskIfOneApproved(String taskSecretId) {
        List<Offer> offersFromTask = offerRepository.findAllActiveOffersFromTask(
                taskRepository.findTaskBySecretId(taskSecretId).getId());
        for (Offer offer: offersFromTask) {
            offer.setOfferStatus(OfferStatus.REJECTED);
            offerRepository.save(offer);
        }
    }

    public List<OfferDto> geExecutorsActiveOffers(String username) {
        User user = userService.findUserByUsername(username);
        List<Offer> offers =  offerRepository.findActiveOffersForExecutor(user.getId());
        return offers.stream()
                .map(OfferDtoFromOffer::offerConverter)
                .collect(Collectors.toList());
    }
}
