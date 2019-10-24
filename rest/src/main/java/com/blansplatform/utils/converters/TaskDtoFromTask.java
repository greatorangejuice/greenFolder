/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.TaskDto;
import com.blansplatform.entity.Task;
import com.blansplatform.entity.User;

public class TaskDtoFromTask {

    public static TaskDto taskConverter(Task task) {
        return new TaskDto(
                task.getSecretId(),
                task.getName(),
                task.getDeadline(),
                task.getType(),
                task.getSpecification(),
                task.getDescription(),
                task.getTaskStatus(),
                task.getSubject(),
                task.getFaculty(),
                task.getKeywords(),
                task.getCustomer().getUsername(),
                userNameNullPointer(task.getExecutor()),
                task.getCourse(),
                task.getUniversity()
        );
    }

    private static String userNameNullPointer(User user) {
        if (user == null) {
            return "no executor";
        } else return user.getUsername();
    }
}
