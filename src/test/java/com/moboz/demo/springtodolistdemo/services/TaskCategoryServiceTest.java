package com.moboz.demo.springtodolistdemo.services;

import com.moboz.demo.springtodolistdemo.dtos.TaskCategoryDTO;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskCategoryMV;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskMV;
import com.moboz.demo.springtodolistdemo.services.task.TaskService;
import com.moboz.demo.springtodolistdemo.services.taskCategory.TaskCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskCategoryServiceTest {

    private final TaskCategoryService taskCategoryService;
    private final TaskService taskService;

    @Autowired
    TaskCategoryServiceTest(TaskCategoryService taskCategoryService, TaskService taskService) {
        this.taskCategoryService = taskCategoryService;
        this.taskService = taskService;
    }

    @Test
    public void runTest(){

        TaskCategoryMV taskCategoryMV = TaskCategoryMV.builder()
                .categoryName("TestCategory")
                .build();

        TaskCategoryDTO taskCategoryDTO = taskCategoryService.save(taskCategoryMV);

        TaskMV task = TaskMV.builder()
                .user(1L)
                .title("Test1")
                .description("Test1")
                .categoryId(taskCategoryDTO.getId())
                .build();

        taskService.save(task);

        TaskMV task2 = TaskMV.builder()
                .user(1L)
                .title("Test2")
                .description("Test2")
                .categoryId(taskCategoryDTO.getId())
                .build();

        taskService.save(task2);

        System.out.println(taskCategoryService.listAll());
        System.out.println(taskCategoryService.getAllTasks(taskCategoryDTO.getId()));
        System.out.println(taskCategoryService.findById(taskCategoryDTO.getId()));
        System.out.println(taskCategoryService.getDTOById(taskCategoryDTO.getId()));

        taskCategoryService.delete(1L);

        System.out.println(taskCategoryService.listAll());

    }

}