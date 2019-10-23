/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.TaskDto;
import com.blansplatform.entity.Task;
import com.blansplatform.entity.User;
import com.blansplatform.enumeration.TaskStatus;
import com.blansplatform.repository.TaskRepository;
import com.blansplatform.repository.UserRepository;
import com.blansplatform.utils.converters.TaskDtoFromTask;
import com.blansplatform.utils.converters.TaskFromTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskDto> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(TaskDtoFromTask::taskConverter)
                .collect(Collectors.toList());
    }

    public TaskDto findTaskById(Long id) {
        Task task = taskRepository.findTaskById(id);
        if (task == null) {
            throw new EntityNotFoundException("task not found");
        }
        return TaskDtoFromTask.taskConverter(task);
    }

    public TaskDto findTaskBySecretId(String secretId) {
        Task task = taskRepository.findTaskBySecretId(secretId);
        if (task == null) {
            throw new EntityNotFoundException("task not found");
        }
        return TaskDtoFromTask.taskConverter(task);
    }

    public void addTask(TaskDto taskDto) {
        Task task = TaskFromTaskDto.taskConverter(taskDto);
        task.setTaskStatus(TaskStatus.ACTIVE);
        task.setSecretId(UUID.randomUUID().toString());
        task.setCustomer(userRepository.findFirstUserByUsername(taskDto.getCustomer()));
        taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }
}
