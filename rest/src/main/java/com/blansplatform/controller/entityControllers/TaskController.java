package com.blansplatform.controller.entityControllers;

import com.blansplatform.entity.Task;
import com.blansplatform.service.entityServices.TaskService;
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
    public List<Task> findAllTask() {
        return taskService.findAll();
    }

    @GetMapping(path = "/{id}")
    public Task findTaskById(@PathVariable Long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping
    public @ResponseBody
    Response addNewTask(@RequestBody Task task) {
        taskService.addTask(task);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }

    @DeleteMapping
    public @ResponseBody Response deleteTask(@RequestBody Task task) {
        taskService.deleteTask(task);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

    @PutMapping
    public @ResponseBody Response updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
        return Response.status(Response.Status.CREATED.getStatusCode()).build();
    }
}
