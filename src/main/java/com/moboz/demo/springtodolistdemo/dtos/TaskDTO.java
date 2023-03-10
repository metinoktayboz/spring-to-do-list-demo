package com.moboz.demo.springtodolistdemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTO {
    private Date due_date;
    private String title;
    private String description;
    private Long user;
    private Long id;
    private Long categoryId;
    private List<TaskCommentDTO> comments;
    private List<TaskAttachmentDTO> attachments;
}
