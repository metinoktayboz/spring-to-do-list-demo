package com.moboz.demo.springtodolistdemo.converters.entityToDto;

import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.entities.TaskAttachment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskAttachmentEntityToDTO implements Converter<TaskAttachment, TaskAttachmentDTO> {
    @Override
    public TaskAttachmentDTO convert(TaskAttachment taskAttachment) {
        return TaskAttachmentDTO.builder()
                .id(taskAttachment.getId())
                .contentType(taskAttachment.getContentType())
                .data(taskAttachment.getData())
                .fileName(taskAttachment.getFileName())
                .taskId(taskAttachment.getTask().getId())
                .build();
    }
}
