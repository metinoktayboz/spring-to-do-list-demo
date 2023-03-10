package com.moboz.demo.springtodolistdemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCategoryDTO {
    private Long id;
    private String categoryName;
    private List<TaskDTO> tasks;
}
