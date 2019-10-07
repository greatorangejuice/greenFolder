/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.TaskDto;
import com.blansplatform.entity.Task;

public class TaskDtoFromTask {

    public static TaskDto taskConverter(Task task) {
        return new TaskDto(
                task.getId(),
                task.getName(),
                task.getSpecification(),
                task.getTaskStatus(),
                task.getSubject(),
                task.getFaculty(),
                task.getKeywords()
        );
    }
}
