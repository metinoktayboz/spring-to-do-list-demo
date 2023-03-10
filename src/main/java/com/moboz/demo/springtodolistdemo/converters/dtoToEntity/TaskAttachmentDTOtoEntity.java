package com.moboz.demo.springtodolistdemo.converters.dtoToEntity;

import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.entities.Task;
import com.moboz.demo.springtodolistdemo.entities.TaskAttachment;
import com.moboz.demo.springtodolistdemo.services.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskAttachmentDTOtoEntity implements Converter<TaskAttachmentDTO, TaskAttachment> {

    private TaskService taskService;

    @Autowired
    public void setObjects(@Lazy TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public TaskAttachment convert(TaskAttachmentDTO taskAttachmentDTO) {
        Task task = taskService.findById(taskAttachmentDTO.getTaskId());
        TaskAttachment taskAttachment = TaskAttachment.builder()
                .id(taskAttachmentDTO.getId())
                .contentType(taskAttachmentDTO.getContentType())
                .data(taskAttachmentDTO.getData())
                .fileName(taskAttachmentDTO.getFileName())
                .task(task)
                .build();

        return taskAttachment;
    }
}
