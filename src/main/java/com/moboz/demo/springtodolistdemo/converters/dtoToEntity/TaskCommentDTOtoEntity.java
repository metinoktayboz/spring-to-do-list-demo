package com.moboz.demo.springtodolistdemo.converters.dtoToEntity;

import com.moboz.demo.springtodolistdemo.dtos.TaskCommentDTO;
import com.moboz.demo.springtodolistdemo.entities.Task;
import com.moboz.demo.springtodolistdemo.entities.TaskComment;
import com.moboz.demo.springtodolistdemo.entities.Users;
import com.moboz.demo.springtodolistdemo.services.task.TaskService;
import com.moboz.demo.springtodolistdemo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskCommentDTOtoEntity implements Converter<TaskCommentDTO, TaskComment> {

    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public TaskCommentDTOtoEntity(@Lazy UserService userService,
                                  @Lazy TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override
    public TaskComment convert(TaskCommentDTO taskCommentDTO) {
        Task task = taskService.findById(taskCommentDTO.getTaskId());
        Users user = userService.findById(taskCommentDTO.getUserId());
        return TaskComment.builder()
                .id(taskCommentDTO.getId())
                .commentText(taskCommentDTO.getCommentText())
                .createdAt(taskCommentDTO.getCreatedAt())
                .task(task)
                .user(user)
                .build();
    }
}
