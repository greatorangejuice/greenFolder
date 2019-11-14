/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.OfferDto;
import com.blansplatform.entity.Offer;
import com.blansplatform.entity.User;
import com.blansplatform.repository.OfferRepository;
import com.blansplatform.repository.TaskRepository;
import com.blansplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfferFromOfferDto {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public OfferFromOfferDto(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public  Offer convert(OfferDto offerDto) {
        return new Offer(
                taskRepository.findTaskBySecretId(offerDto.getSecretId()),
                userRepository.findFirstUserByUsername(offerDto.getExecutor()),
                userRepository.findFirstUserByUsername(offerDto.getCustomer()),
                offerDto.getBid(),
                offerDto.getOfferStatus(),
                offerDto.getComment()
        );
    }

}
