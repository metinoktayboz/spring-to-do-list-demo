package com.moboz.demo.springtodolistdemo.converters.entityToDto;

import com.moboz.demo.springtodolistdemo.dtos.TaskCommentDTO;
import com.moboz.demo.springtodolistdemo.entities.TaskComment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskCommentEntityToDTO implements Converter<TaskComment, TaskCommentDTO>{
    @Override
    public TaskCommentDTO convert(TaskComment taskComment) {

        return TaskCommentDTO.builder()
                .commentText(taskComment.getCommentText())
                .id(taskComment.getId())
                .taskId(taskComment.getTask().getId())
                .createdAt(taskComment.getCreatedAt())
                .userId(taskComment.getUser().getId())
                .build();
    }
}
