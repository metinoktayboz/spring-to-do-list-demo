package com.moboz.demo.springtodolistdemo.controllers;

import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.models.TaskAttachmentList;
import com.moboz.demo.springtodolistdemo.models.TaskCommentList;
import com.moboz.demo.springtodolistdemo.models.TaskList;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskMV;
import com.moboz.demo.springtodolistdemo.services.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/tc")
@CrossOrigin(origins = "*", allowedHeaders = "*")
    public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;;
    }

    @Operation( summary = "Save Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping(value= "/st")
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskMV task){
        TaskDTO savedTask = taskService.save(task);
        return ResponseEntity.created(URI.create("/st/" + savedTask.getId())).body(savedTask);
    }

    @Operation( summary = "Find by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/fbi")
    public ResponseEntity<TaskDTO> findById(@RequestParam Long id){
        TaskDTO task = taskService.getDTOById(id);
        return ResponseEntity.created(URI.create("/st/" + task.getId())).body(task);
    }

    @Operation( summary = "Delete by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @DeleteMapping(value= "/dbi")
    public ResponseEntity<?> deleteById(@RequestParam Long id){
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation( summary = "Get All Tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/gat")
    public ResponseEntity<TaskList> getAllTasks(){
        TaskList taskList = new TaskList(taskService.listAll());
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @Operation( summary = "Update Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping(value= "/ut/{id}")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskMV task, @PathVariable Long id){
        TaskDTO updatedTask = taskService.update(task, id);
        return ResponseEntity.created(URI.create("/st/" + updatedTask.getId())).body(updatedTask);
    }

    @Operation( summary = "Get Comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/gtc/{id}")
    public ResponseEntity<TaskCommentList> getComments(@PathVariable Long id){
        TaskCommentList taskCommentList = new TaskCommentList(taskService.getTaskComments(id));
        return new ResponseEntity<>(taskCommentList, HttpStatus.OK);
    }


    @Operation( summary = "Get Attachments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/gta/{id}")
    public ResponseEntity<TaskAttachmentList> getAttachments(@PathVariable Long id){
        TaskAttachmentList taskAttachmentList = new TaskAttachmentList(taskService.getTaskAttachments(id));
        return new ResponseEntity<>(taskAttachmentList, HttpStatus.OK);
    }

}
