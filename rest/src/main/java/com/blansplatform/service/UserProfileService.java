/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.UserProfileDto;
import com.blansplatform.entity.User;
import com.blansplatform.repository.UserRepository;
import com.blansplatform.utils.converters.MessageDtoFromMessage;
import com.blansplatform.utils.converters.OfferDtoFromOffer;
import com.blansplatform.utils.converters.TaskDtoFromTask;
import com.blansplatform.utils.converters.UserDtoFromUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserProfileService {
    private final UserRepository userRepository;

    @Autowired
    public UserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileDto userProfile(Long id) {
        UserProfileDto userProfileDto = new UserProfileDto();
        User userFromDb = userRepository.findUserById(id);
        userProfileDto.setUser(UserDtoFromUser.userDtoConverter(userFromDb));
        userProfileDto.setTasksLikeExecutor(userFromDb.getExecutorTasks().stream()
                .map(TaskDtoFromTask::taskConverter)
                .collect(Collectors.toList()));
        userProfileDto.setTasksLikeCustomer(userFromDb.getCustomerTasks()
                .stream().map(TaskDtoFromTask::taskConverter)
                .collect(Collectors.toList()));
        userProfileDto.setInboxMessages(userFromDb.getInboxMessages().stream()
                .map(MessageDtoFromMessage::MessageConverter)
                .collect(Collectors.toList()));
        userProfileDto.setOutgoingMessages(userFromDb.getOutgoingMessage().stream()
                .map(MessageDtoFromMessage::MessageConverter)
                .collect(Collectors.toList()));
        userProfileDto.setInboxOffers(userFromDb.getCustomerOffers().stream()
                .map(OfferDtoFromOffer::offerConverter)
                .collect(Collectors.toList()));
        userProfileDto.setOutgoingOffers(userFromDb.getExecutorOffers().stream()
                .map(OfferDtoFromOffer::offerConverter)
                .collect(Collectors.toList()));
        return userProfileDto;
    }





}
