package com.blansplatform.service;

import com.blansplatform.entity.Task;
import com.blansplatform.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TaskService {
    final private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        Task task = taskRepository.findTaskById(id);
        if (task == null) {
            throw new EntityNotFoundException("task not found");
        }
        return task;
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
