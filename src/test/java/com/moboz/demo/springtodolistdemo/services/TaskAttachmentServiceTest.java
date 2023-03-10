package com.moboz.demo.springtodolistdemo.services;

import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskAttachmentMV;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskMV;
import com.moboz.demo.springtodolistdemo.services.task.TaskService;
import com.moboz.demo.springtodolistdemo.services.taskAttachment.TaskAttachmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class TaskAttachmentServiceTest {

    private final TaskService taskService;
    private final TaskAttachmentService taskAttachmentService;

    @Autowired
    TaskAttachmentServiceTest(TaskService taskService,
                              TaskAttachmentService taskAttachmentService) {
        this.taskService = taskService;
        this.taskAttachmentService = taskAttachmentService;
    }

    @Test
    public void runTest() throws IOException {
        TaskMV task = TaskMV.builder()
                .title("TestTitle1")
                .description("TestDescription1")
                .user(1L)
                .categoryId(1L)
                .build();
        TaskDTO taskDTO = taskService.save(task);

        TaskAttachmentMV taskAttachmentMV = TaskAttachmentMV.builder()
                .taskId(taskDTO.getId())
                .fileName("TestFile1")
                .contentType("TestType1")
                .build();

        TaskAttachmentDTO taskAttachmentDTO1 = taskAttachmentService.save(taskAttachmentMV);

        TaskAttachmentMV taskAttachmentMV2 = TaskAttachmentMV.builder()
                .taskId(taskDTO.getId())
                .fileName("TestFile1")
                .contentType("TestType1")
                .build();

        TaskAttachmentDTO taskAttachmentDTO2 = taskAttachmentService.save(taskAttachmentMV2);

        System.out.println(taskAttachmentService.listAll());
        System.out.println(taskAttachmentService.findById(taskAttachmentDTO2.getId()));
        System.out.println(taskAttachmentService.getDTOById(taskAttachmentDTO2.getId()));

        taskAttachmentService.delete(taskAttachmentDTO1.getId());

        System.out.println(taskService.getDTOById(taskDTO.getId()));
    }

}