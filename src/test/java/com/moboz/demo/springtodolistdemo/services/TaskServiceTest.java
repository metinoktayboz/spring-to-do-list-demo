package com.moboz.demo.springtodolistdemo.services;

import com.moboz.demo.springtodolistdemo.dtos.TaskCategoryDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.entities.Users;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskAttachmentMV;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskCategoryMV;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskCommentMV;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskMV;
import com.moboz.demo.springtodolistdemo.repos.UserRepository;
import com.moboz.demo.springtodolistdemo.services.task.TaskService;
import com.moboz.demo.springtodolistdemo.services.taskAttachment.TaskAttachmentService;
import com.moboz.demo.springtodolistdemo.services.taskCategory.TaskCategoryService;
import com.moboz.demo.springtodolistdemo.services.taskComment.TaskCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class TaskServiceTest {

    private final TaskService taskService;
    private final UserRepository userRepository;
    private final TaskCategoryService taskCategoryService;
    private final TaskCommentService taskCommentService;
    private final TaskAttachmentService taskAttachmentService;

    @Autowired
    TaskServiceTest(TaskService taskService,
                    UserRepository userRepository,
                    TaskCategoryService taskCategoryService,
                    TaskCommentService taskCommentService,
                    TaskAttachmentService taskAttachmentService) {
        this.taskService = taskService;
        this.userRepository = userRepository;
        this.taskCategoryService = taskCategoryService;
        this.taskCommentService = taskCommentService;
        this.taskAttachmentService = taskAttachmentService;
    }

    @Test
    public void runTest() throws IOException {
        Users user = Users.builder()
                .firstName("selam")
                .lastName("xd")
                .email("sadsa@gmail.com")
                .build();
        userRepository.save(user);

        TaskCategoryMV taskCategoryMV = TaskCategoryMV.builder()
                .categoryName("IT")
                .build();
        TaskCategoryDTO taskCategory = taskCategoryService.save(taskCategoryMV);

        TaskMV task1 = TaskMV.builder()
                .title("Task1")
                .description("Description1")
                .user(user.getId())
                .categoryId(taskCategory.getId())
                .build();

        TaskDTO taskDTO1 = taskService.save(task1);
        TaskMV task2 = TaskMV.builder()
                .title("Task2")
                .description("Description2")
                .user(user.getId())
                .categoryId(taskCategory.getId())
                .build();
        TaskDTO taskDTO2 = taskService.save(task2);
        TaskMV taskUpdated = new TaskMV();
        taskUpdated.setTitle("updatedTitle");
        taskUpdated.setDescription("updatedDescripion");
        taskUpdated.setCategoryId(2L);
        taskService.update(taskUpdated,taskDTO1.getId());
        taskService.listAll()
                .forEach(System.out::println);
        taskService.delete(taskDTO1.getId());
        System.out.println(taskService.getDTOById(taskDTO2.getId()));
        System.out.println(taskService.findById(taskDTO2.getId()));

        TaskAttachmentMV taskAttachmentMV = TaskAttachmentMV.builder()
                .taskId(taskDTO2.getId())
                .contentType("testContentType")
                .fileName("testFileName")
                .build();
        taskAttachmentService.save(taskAttachmentMV);

        TaskCommentMV taskCommentMV = TaskCommentMV.builder()
                .commentText("commentTextTest")
                .userId(user.getId())
                .taskId(taskDTO2.getId())
                .build();

        taskCommentService.save(taskCommentMV);

        System.out.println(taskService.getTaskAttachments(taskDTO2.getId()));
        System.out.println(taskService.getTaskComments(taskDTO2.getId()));



    }

}