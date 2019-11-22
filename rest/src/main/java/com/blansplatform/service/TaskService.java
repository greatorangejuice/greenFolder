/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.TaskDto;

import java.util.List;

public interface TaskService {

    List<TaskDto> findAll();

    TaskDto findTaskById(Long id);

    TaskDto findTaskBySecretId(String secretId);

    TaskDto addTask(TaskDto taskDto);

    void deleteTask(TaskDto taskDto);

    void updateTask(TaskDto taskDto);

    List<TaskDto> findAllActiveTasks();

    void deleteTaskBySecretId(String secretId);

}
