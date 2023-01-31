package com.testdemo.todoapp;

import com.testdemo.todoapp.persistence.entity.TaskStatus;

/**
 * A Projection for the {@link com.testdemo.todoapp.persistence.entity.Task} entity
 */
public interface TaskInfo {
    TaskStatus getStatus();
}