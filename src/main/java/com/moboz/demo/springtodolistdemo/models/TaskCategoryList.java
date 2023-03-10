package com.moboz.demo.springtodolistdemo.models;

import com.moboz.demo.springtodolistdemo.dtos.TaskCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCategoryList {
    private List<TaskCategoryDTO> taskCategories;
}
