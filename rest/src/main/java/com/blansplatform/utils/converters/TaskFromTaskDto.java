/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.TaskDto;
import com.blansplatform.entity.Task;
import com.blansplatform.repository.OfferRepository;
import com.blansplatform.repository.TaskRepository;
import com.blansplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskFromTaskDto {

    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskFromTaskDto(UserRepository userRepository, OfferRepository offerRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.taskRepository = taskRepository;
    }


    public Task convert(TaskDto taskDto) {
        return new Task(
                taskDto.getDeadline(),
                taskDto.getType(),
                taskDto.getSecretId(),
                taskDto.getName(),
                taskDto.getDescription(),
                taskDto.getSpecification(),
                taskDto.getTaskStatus(),
                taskDto.getSubject(),
                taskDto.getFaculty(),
                userRepository.findFirstUserByUsername(taskDto.getCustomer()),
                userRepository.findFirstUserByUsername(taskDto.getExecutor()),
                offerRepository.findAllOffersForTask(taskRepository.findTaskBySecretId(taskDto.getSecretId()).getId()),
                taskDto.getKeywords(),
                taskDto.getCourse(),
                taskDto.getUniversity()
        );
    }

}
