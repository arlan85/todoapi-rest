package com.testdemo.todoapp.service;

import com.testdemo.todoapp.dto.TaskInDTO;
import com.testdemo.todoapp.exceptions.TodoExceptions;
import com.testdemo.todoapp.mapper.TaskInDTOToTask;
import com.testdemo.todoapp.persistence.entity.Task;
import com.testdemo.todoapp.persistence.entity.TaskStatus;
import com.testdemo.todoapp.persistence.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskInDTOToTask mapper;
    public TaskService(TaskRepository taskRepository, TaskInDTOToTask mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return this.taskRepository.save(task);
    }

    public List<Task> findAll(){
        return this.taskRepository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status) {
        return this.taskRepository.findAllByStatus(status);
    }

    @Transactional
    public void markAsFinished(Long id) {
        Optional<Task> optTask = this.taskRepository.findById(id);
        if (optTask.isEmpty()){
            throw  new TodoExceptions("Task not found on system", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.markAsFinished(id);
    }

    public void deleteById(Long id) {
        Optional<Task> optTask = this.taskRepository.findById(id);
        if (optTask.isEmpty()){
            throw  new TodoExceptions("Task not found on system", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.deleteById(id);
    }
}