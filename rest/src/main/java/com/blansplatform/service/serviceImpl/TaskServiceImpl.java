/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service.serviceImpl;

import com.blansplatform.dto.TaskDto;
import com.blansplatform.entity.Task;
import com.blansplatform.enumeration.TaskStatus;
import com.blansplatform.repository.TaskRepository;
import com.blansplatform.repository.UserRepository;
import com.blansplatform.service.TaskService;
import com.blansplatform.utils.converters.TaskDtoFromTask;
import com.blansplatform.utils.converters.TaskFromTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskFromTaskDto taskFromTaskDto;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, TaskFromTaskDto taskFromTaskDto) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskFromTaskDto = taskFromTaskDto;
    }

    @Override
    public List<TaskDto> findAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(TaskDtoFromTask::convert)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto findTaskById(Long id) {
        Task task = taskRepository.findTaskById(id);
        if (task == null) {
            throw new EntityNotFoundException("task not found");
        }
        return TaskDtoFromTask.convert(task);
    }

    @Override
    public TaskDto findTaskBySecretId(String secretId) {
        Task task = taskRepository.findTaskBySecretId(secretId);
        if (task == null) {
            throw new EntityNotFoundException("task not found");
        }
        return TaskDtoFromTask.convert(task);
    }

    @Override
    public TaskDto addTask(TaskDto taskDto) {
        Task task = taskFromTaskDto.convert(taskDto);
        task.setTaskStatus(TaskStatus.ACTIVE);
        task.setSecretId(UUID.randomUUID().toString());
        task.setCustomer(userRepository.findFirstUserByUsername(taskDto.getCustomer()));
        return TaskDtoFromTask.convert(taskRepository.save(task));
    }

    @Override
    public void deleteTask(TaskDto taskDto) {
        Task task = taskFromTaskDto.convert(taskDto);
        task.setId(taskRepository.findTaskBySecretId(taskDto.getSecretId()).getId());
        taskRepository.delete(task);
    }

    @Override
    public void updateTask(TaskDto taskDto) {
        Task task = taskFromTaskDto.convert(taskDto);
        task.setId(taskRepository.findTaskBySecretId(taskDto.getSecretId()).getId());
        taskRepository.save(task);
    }

    @Override
    public List<TaskDto> findAllActiveTasks() {
        List<Task> tasks = taskRepository.findAllActiveTasks();
        return  tasks.stream()
                .map(TaskDtoFromTask::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTaskBySecretId(String secretId) {
        Task taskFromDb = taskRepository.findTaskBySecretId(secretId);
        if (taskFromDb == null) {
            throw new EntityNotFoundException("task not found");
        }
        taskRepository.delete(taskFromDb);
    }
}
