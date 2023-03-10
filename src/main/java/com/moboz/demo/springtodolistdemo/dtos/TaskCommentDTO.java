package com.moboz.demo.springtodolistdemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCommentDTO {
    private Long id;
    private Long taskId;
    private Long userId;
    private String commentText;
    private Date createdAt;
}
