/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.service;

import com.blansplatform.dto.TaskDto;
import com.blansplatform.entity.Task;
import com.blansplatform.entity.User;
import com.blansplatform.repository.TaskRepository;
import com.blansplatform.utils.converters.TaskDtoFromTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    final private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }
}
