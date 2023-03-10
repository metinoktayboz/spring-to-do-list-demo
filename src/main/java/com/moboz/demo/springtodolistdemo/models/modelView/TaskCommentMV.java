package com.moboz.demo.springtodolistdemo.models.modelView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCommentMV {
    private Long taskId;
    private Long userId;
    private String commentText;
    private Date createdAt;
}
