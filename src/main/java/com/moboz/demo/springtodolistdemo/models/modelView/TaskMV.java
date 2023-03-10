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
public class TaskMV {
    private Date due_date;
    private String title;
    private String description;
    private Long user;
    private Long categoryId;
}
