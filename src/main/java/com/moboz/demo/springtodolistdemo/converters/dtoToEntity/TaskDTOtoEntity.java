package com.moboz.demo.springtodolistdemo.converters.dtoToEntity;

import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskCommentDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.entities.*;
import com.moboz.demo.springtodolistdemo.services.taskCategory.TaskCategoryService;
import com.moboz.demo.springtodolistdemo.services.user.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskDTOtoEntity implements Converter<TaskDTO, Task> {

    private final UserService userService;
    private final TaskCategoryService taskCategoryService;
    private final TaskCommentDTOtoEntity taskCommentDTOtoEntity;
    private final TaskAttachmentDTOtoEntity taskAttachmentDTOtoEntity;




    public TaskDTOtoEntity(@Lazy UserService userService,
                           @Lazy TaskCategoryService taskCategoryService,
                           @Lazy TaskCommentDTOtoEntity taskCommentDTOtoEntity,
                           @Lazy TaskAttachmentDTOtoEntity taskAttachmentDTOtoEntity) {
        this.userService = userService;
        this.taskCategoryService = taskCategoryService;
        this.taskCommentDTOtoEntity = taskCommentDTOtoEntity;
        this.taskAttachmentDTOtoEntity = taskAttachmentDTOtoEntity;
    }

    @Override
    public Task convert(TaskDTO taskDTO) {

        List<TaskCommentDTO> comments = taskDTO.getComments();
        List<TaskComment> commentsEntity = comments != null
                ? comments.stream()
                .map(taskCommentDTOtoEntity::convert)
                .collect(Collectors.toList())
                : new ArrayList<>();


        List<TaskAttachmentDTO> attachments = taskDTO.getAttachments();
        List<TaskAttachment> attachmentsEntity = attachments != null
                ? attachments.stream()
                .map(taskAttachmentDTOtoEntity::convert)
                .collect(Collectors.toList())
                : new ArrayList<>();

        Users user = userService.findById(taskDTO.getUser());
        TaskCategory taskCategory = taskCategoryService.findById(taskDTO.getCategoryId());

        return Task.builder()
                .title(taskDTO.getTitle())
                .description(taskDTO.getDescription())
                .due_date(taskDTO.getDue_date())
                .user(user)
                .attachments(attachmentsEntity)
                .comments(commentsEntity)
                .category(taskCategory)
                .id(taskDTO.getId())
                .build();
    }
}
