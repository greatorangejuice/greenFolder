/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.utils.converters;

import com.blansplatform.dto.TaskDto;
import com.blansplatform.entity.Task;

public class TaskFromTaskDto {

    public static Task taskConverter(TaskDto taskDto) {
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
                taskDto.getKeywords(),
                taskDto.getCourse(),
                taskDto.getUniversity()
        );
    }

}
