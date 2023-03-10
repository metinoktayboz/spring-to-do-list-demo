package com.moboz.demo.springtodolistdemo.converters.dtoToEntity;
import com.moboz.demo.springtodolistdemo.dtos.TaskCategoryDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.entities.Task;
import com.moboz.demo.springtodolistdemo.entities.TaskCategory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskCategoryDTOtoEntity implements Converter<TaskCategoryDTO, TaskCategory>{

    private final TaskDTOtoEntity taskDTOtoEntity;


    public TaskCategoryDTOtoEntity(@Lazy TaskDTOtoEntity taskDTOtoEntity) {
        this.taskDTOtoEntity = taskDTOtoEntity;
    }

    @Override
    public TaskCategory convert(TaskCategoryDTO taskCategoryDTO) {

        List<TaskDTO> tasksDTO = taskCategoryDTO.getTasks();
        List<Task> tasks = tasksDTO != null
                ? tasksDTO.stream()
                .map(taskDTOtoEntity::convert)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return TaskCategory.builder()
                .categoryName(taskCategoryDTO.getCategoryName())
                .id(taskCategoryDTO.getId())
                .tasks(tasks)
                .build();
    }
}
