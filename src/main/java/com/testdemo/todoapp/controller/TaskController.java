package com.testdemo.todoapp.controller;

import com.testdemo.todoapp.dto.TaskInDTO;
import com.testdemo.todoapp.persistence.entity.Task;
import com.testdemo.todoapp.persistence.entity.TaskStatus;
import com.testdemo.todoapp.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

/**
 * Task Controller
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO) {
        return this.taskService.createTask(taskInDTO);
    }

    @GetMapping
    public List<Task> findAll(){
        return this.taskService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findByStatus(@PathVariable("status") TaskStatus status){
        return this.taskService.findAllByTaskStatus(status);
    }

    @PatchMapping("mark-finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id){
        this.taskService.markAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.taskService.deleteById(id);
        
        return ResponseEntity.noContent().build();
    }
}
