package com.moboz.demo.springtodolistdemo.converters.entityToDto;

import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskCommentDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.entities.Task;
import com.moboz.demo.springtodolistdemo.entities.TaskAttachment;
import com.moboz.demo.springtodolistdemo.entities.TaskComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskEntityToDTO implements Converter<Task, TaskDTO> {

    private final TaskCommentEntityToDTO taskCommentEntityToDTO;
    private final TaskAttachmentEntityToDTO taskAttachmentEntityToDTO;

    @Autowired
    public TaskEntityToDTO(@Lazy TaskCommentEntityToDTO taskCommentEntityToDTO,
                           @Lazy TaskAttachmentEntityToDTO taskAttachmentEntityToDTO) {
        this.taskCommentEntityToDTO = taskCommentEntityToDTO;
        this.taskAttachmentEntityToDTO = taskAttachmentEntityToDTO;
    }

    @Override
    public TaskDTO convert(Task task) {
        List<TaskComment> taskComments = task.getComments();
        List<TaskCommentDTO> comments = taskComments != null
                ? taskComments.stream()
                .map(taskCommentEntityToDTO::convert)
                .collect(Collectors.toList())
                : new ArrayList<>();

         List<TaskAttachment> attachmentsEntity= task.getAttachments();
        List<TaskAttachmentDTO> attachments = attachmentsEntity != null
                ? attachmentsEntity.stream()
                .map(taskAttachmentEntityToDTO::convert)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return TaskDTO.builder()
                .categoryId(task.getCategory().getId())
                .description(task.getDescription())
                .due_date(task.getDue_date())
                .title(task.getTitle())
                .user(task.getUser().getId())
                .id(task.getId())
                .attachments(attachments)
                .comments(comments)
                .build();
    }
}
