package com.moboz.demo.springtodolistdemo.services;

import com.moboz.demo.springtodolistdemo.dtos.UserDTO;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskMV;
import com.moboz.demo.springtodolistdemo.models.modelView.UserMV;
import com.moboz.demo.springtodolistdemo.services.task.TaskService;
import com.moboz.demo.springtodolistdemo.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    UserServiceTest(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @Test
    public void runTest(){

        UserMV userMV = UserMV.builder()
                .firstName("Test")
                .lastName("TestSon")
                .email("test@test1.com")
                .build();

        UserDTO userDTO = userService.save(userMV);

        UserMV userMV2 = UserMV.builder()
                .firstName("Test2")
                .lastName("TestSon2")
                .email("test@test2.com")
                .build();

        UserDTO userDTO2 = userService.save(userMV2);

        TaskMV task = TaskMV.builder()
                .categoryId(1L)
                .user(userDTO2.getId())
                .title("Test1Title")
                .description("Test1Description")
                .build();
        taskService.save(task);

        userService.listAll()
                .forEach(System.out::println);

        userService.delete(userDTO.getId());

        System.out.println(userService.findById(userDTO2.getId()));
        System.out.println(userService.getDTOById(userDTO2.getId()));


    }

}