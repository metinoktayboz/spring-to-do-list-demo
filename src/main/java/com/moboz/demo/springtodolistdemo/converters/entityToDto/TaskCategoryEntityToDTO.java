package com.moboz.demo.springtodolistdemo.converters.entityToDto;

import com.moboz.demo.springtodolistdemo.dtos.TaskCategoryDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.entities.Task;
import com.moboz.demo.springtodolistdemo.entities.TaskCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskCategoryEntityToDTO implements Converter<TaskCategory, TaskCategoryDTO> {

    private final TaskEntityToDTO taskEntityToDTO;

    @Autowired
    public TaskCategoryEntityToDTO(@Lazy TaskEntityToDTO taskEntityToDTO) {
        this.taskEntityToDTO = taskEntityToDTO;
    }

    @Override
    public TaskCategoryDTO convert(TaskCategory taskCategory) {

        List<Task> tasks = taskCategory.getTasks();
        List<TaskDTO> tasksDTO = tasks != null
                ? tasks.stream()
                .map(taskEntityToDTO::convert)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return TaskCategoryDTO.builder()
                .categoryName(taskCategory.getCategoryName())
                .id(taskCategory.getId())
                .tasks(tasksDTO)
                .build();
    }
}
