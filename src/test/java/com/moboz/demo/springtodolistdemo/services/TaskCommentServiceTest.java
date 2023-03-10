package com.moboz.demo.springtodolistdemo.services;

import com.moboz.demo.springtodolistdemo.dtos.TaskCommentDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskAttachmentMV;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskCommentMV;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskMV;
import com.moboz.demo.springtodolistdemo.services.task.TaskService;
import com.moboz.demo.springtodolistdemo.services.taskComment.TaskCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskCommentServiceTest {

    private final TaskService taskService;
    private final TaskCommentService taskCommentService;

    @Autowired
    TaskCommentServiceTest(TaskService taskService, TaskCommentService taskCommentService) {
        this.taskService = taskService;
        this.taskCommentService = taskCommentService;
    }

    @Test
    public void runTest(){
        TaskMV task = TaskMV.builder()
                .title("TestTitle1")
                .description("TestDescription1")
                .user(1L)
                .categoryId(1L)
                .build();
        TaskDTO taskDTO = taskService.save(task);

        TaskCommentMV taskCommentMV1 = TaskCommentMV.builder()
                .userId(1L)
                .taskId(taskDTO.getId())
                .commentText("Test Comment1")
                .build();

        TaskCommentDTO taskCommentDTO1 = taskCommentService.save(taskCommentMV1);

        TaskCommentMV taskCommentMV2 = TaskCommentMV.builder()
                .userId(1L)
                .taskId(taskDTO.getId())
                .commentText("Test Comment2")
                .build();

        TaskCommentDTO taskCommentDTO2 = taskCommentService.save(taskCommentMV2);

        System.out.println(taskCommentService.listAll());
        System.out.println(taskCommentService.findById(taskCommentDTO1.getId()));
        System.out.println(taskCommentService.getDTOById(taskCommentDTO1.getId()));

        taskCommentService.delete(taskCommentDTO2.getId());

        System.out.println(taskService.getDTOById(taskDTO.getId()));

    }

}