package com.testdemo.todoapp.mapper;

import com.testdemo.todoapp.dto.TaskInDTO;
import com.testdemo.todoapp.persistence.entity.Task;
import com.testdemo.todoapp.persistence.entity.TaskStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements  IMapper<TaskInDTO, Task>{
    @Override
    public Task map(TaskInDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setStatus(TaskStatus.ON_TIME);
        return task;
    }
}
