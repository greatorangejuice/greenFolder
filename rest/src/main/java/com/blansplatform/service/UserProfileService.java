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

    public UserProfileDto userProfileById(Long id) {
        User userFromDb = userRepository.findUserById(id);
        return userProfileDtoBuilder(userFromDb);
    }

    public UserProfileDto userProfileByUsername(String username) {
        User userFromDb = userRepository.findFirstUserByUsername(username);
        return userProfileDtoBuilder(userFromDb);
    }

    private UserProfileDto userProfileDtoBuilder(User user) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setUser(UserDtoFromUser.userDtoConverter(user));
        userProfileDto.setTasksLikeExecutor(user.getExecutorTasks().stream()
                .map(TaskDtoFromTask::taskConverter)
                .collect(Collectors.toList()));
        userProfileDto.setTasksLikeCustomer(user.getCustomerTasks()
                .stream().map(TaskDtoFromTask::taskConverter)
                .collect(Collectors.toList()));
        userProfileDto.setInboxMessages(user.getInboxMessages().stream()
                .map(MessageDtoFromMessage::MessageConverter)
                .collect(Collectors.toList()));
        userProfileDto.setOutgoingMessages(user.getOutgoingMessage().stream()
                .map(MessageDtoFromMessage::MessageConverter)
                .collect(Collectors.toList()));
        userProfileDto.setInboxOffers(user.getCustomerOffers().stream()
                .map(OfferDtoFromOffer::offerConverter)
                .collect(Collectors.toList()));
        userProfileDto.setOutgoingOffers(user.getExecutorOffers().stream()
                .map(OfferDtoFromOffer::offerConverter)
                .collect(Collectors.toList()));
        return userProfileDto;

    }





}
