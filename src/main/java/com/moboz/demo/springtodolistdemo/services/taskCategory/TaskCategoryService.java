package com.moboz.demo.springtodolistdemo.services.taskCategory;

import com.moboz.demo.springtodolistdemo.dtos.TaskCategoryDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.entities.TaskCategory;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskCategoryMV;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskMV;

import java.util.List;

public interface TaskCategoryService {
    TaskCategoryDTO save(TaskCategoryMV taskCategoryMV);
    List<TaskCategoryDTO> listAll();
    TaskCategory findById(Long id);
    TaskCategoryDTO getDTOById(Long id);
    List<TaskDTO> getAllTasks(Long id);
    void delete(Long id);
    TaskCategoryDTO update(TaskCategoryMV taskCategoryMV, Long categoryId);
}
