/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.TaskDto;
import com.blansplatform.entity.Task;

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
                task.getExecutor().getUsername(),
                task.getCourse()
        );
    }
}
