package com.testdemo.todoapp.persistence.repository;

import com.testdemo.todoapp.persistence.entity.Task;
import com.testdemo.todoapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository  extends JpaRepository<Task, Long> {
    public List<Task> findAllByStatus(TaskStatus status);

    @Modifying
    @Query(value = "update task set finished = true  where ID=:id", nativeQuery = true)
    public void markAsFinished(@Param("id") Long id);
}
