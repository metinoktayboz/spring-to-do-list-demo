package com.moboz.demo.springtodolistdemo;

import com.moboz.demo.springtodolistdemo.dtos.*;
import com.moboz.demo.springtodolistdemo.models.modelView.*;
import com.moboz.demo.springtodolistdemo.services.task.TaskService;
import com.moboz.demo.springtodolistdemo.services.taskAttachment.TaskAttachmentService;
import com.moboz.demo.springtodolistdemo.services.taskCategory.TaskCategoryService;
import com.moboz.demo.springtodolistdemo.services.taskComment.TaskCommentService;
import com.moboz.demo.springtodolistdemo.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringToDoListDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringToDoListDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (UserService userService,
										 TaskService taskService,
										 TaskCategoryService taskCategoryService,
										 TaskAttachmentService taskAttachmentService,
										 TaskCommentService taskCommentService){
		return args -> {
			//Default User
			UserMV user= UserMV.builder()
					.email("test@test.com")
					.firstName("testName")
					.lastName("testLastName")
					.build();
			UserDTO userDTO = userService.save(user);

			//Default Category
			TaskCategoryMV taskCategory = TaskCategoryMV.builder()
					.categoryName("testCategory")
					.build();
			TaskCategoryDTO taskCategoryDTO = taskCategoryService.save(taskCategory);

			//Default Task
			TaskMV task = TaskMV.builder()
					.categoryId(taskCategoryDTO.getId())
					.description("Test Desc.")
					.title("Test Title")
					.user(userDTO.getId())
					.build();
			TaskDTO taskDTO = taskService.save(task);

			//Default Task Comment
			TaskCommentMV taskCommentMV = TaskCommentMV.builder()
					.userId(userDTO.getId())
					.taskId(taskDTO.getId())
					.commentText("Test Comment")
					.build();
			TaskCommentDTO taskCommentDTO = taskCommentService.save(taskCommentMV);

			//Default Task Attachment
			TaskAttachmentMV taskAttachmentMV = TaskAttachmentMV.builder()
					.fileName("Test File")
					.contentType("Test Type")
					.taskId(taskDTO.getId())
					.build();
			TaskAttachmentDTO taskAttachmentDTO = taskAttachmentService.save(taskAttachmentMV);

		};
	}
}
