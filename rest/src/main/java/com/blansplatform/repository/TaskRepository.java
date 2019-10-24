package com.blansplatform.repository;

import com.blansplatform.dto.TaskDto;
import com.blansplatform.entity.Task;
import com.blansplatform.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAll();
    Task findTaskById(Long id);
    @Query(value = "select * from task where customer_id = ?1", nativeQuery = true)
    List<Task> findTaskByCustomerId(@Param("customer_id") Long id);
    @Query(value = "select * from task where executor_id = ?1", nativeQuery = true)
    List<Task> findTaskByExecutorId(@Param("customer_id") Long id);
    Task findTaskBySecretId(String secretId);
    @Query(value = "select * from task where task_status = 0", nativeQuery = true)
    List<Task> findAllActiveTasks();
}
