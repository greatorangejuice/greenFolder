/*
 * Copyright (c) GreenFolder
 */

package com.blansplatform.controller;

import com.blansplatform.dto.TaskDto;
import com.blansplatform.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    final private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public List<TaskDto> findAllTask() {
        return taskService.findAll();
    }

    @GetMapping(path = "/{secretId}")
    public TaskDto findTaskBySecretId(@PathVariable String secretId) {
        return taskService.findTaskBySecretId(secretId);
    }

    @GetMapping("/active")
    public List<TaskDto> findAllActiveTasks() {
        return taskService.findAllActiveTasks();
    }

    @PostMapping
    public TaskDto addNewTask(@RequestBody TaskDto taskDto) {
        return taskService.addTask(taskDto);
    }

    @DeleteMapping
    public @ResponseBody Response deleteTask(@RequestBody TaskDto taskDto) {
        taskService.deleteTask(taskDto);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PutMapping
    public @ResponseBody Response updateTask(@RequestBody TaskDto taskDto) {
        taskService.updateTask(taskDto);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
