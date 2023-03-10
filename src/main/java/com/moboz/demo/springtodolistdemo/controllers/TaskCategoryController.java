package com.moboz.demo.springtodolistdemo.controllers;

import com.moboz.demo.springtodolistdemo.dtos.TaskCategoryDTO;
import com.moboz.demo.springtodolistdemo.models.TaskCategoryList;
import com.moboz.demo.springtodolistdemo.models.TaskList;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskCategoryMV;
import com.moboz.demo.springtodolistdemo.services.taskCategory.TaskCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/tcac")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskCategoryController {
    private final TaskCategoryService taskCategoryService;

    @Autowired
    public TaskCategoryController(TaskCategoryService taskCategoryService) {
        this.taskCategoryService = taskCategoryService;
    }

    @Operation( summary = "Save Task Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping(value= "/stc")
    public ResponseEntity<TaskCategoryDTO> saveTaskCategory(@RequestBody TaskCategoryMV taskCategoryMV){
        TaskCategoryDTO savedTaskCategory = taskCategoryService.save(taskCategoryMV);
        return ResponseEntity.created(URI.create("/st/" + savedTaskCategory.getId())).body(savedTaskCategory);
    }

    @Operation( summary = "Find by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/fbi")
    public ResponseEntity<TaskCategoryDTO> findById(@RequestParam Long id){
        TaskCategoryDTO taskCategoryDTO = taskCategoryService.getDTOById(id);
        return ResponseEntity.created(URI.create("/st/" + taskCategoryDTO.getId())).body(taskCategoryDTO);
    }

    @Operation( summary = "Delete by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @DeleteMapping(value= "/dbi")
    public ResponseEntity<?> deleteById(@RequestParam Long id){
        taskCategoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation( summary = "Get All Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/gac")
    public ResponseEntity<TaskCategoryList> getAllTasks(){
        TaskCategoryList taskCategoryList = new TaskCategoryList(taskCategoryService.listAll());
        return new ResponseEntity<>(taskCategoryList, HttpStatus.OK);
    }

    @Operation( summary = "Get Category Tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/gct")
    public ResponseEntity<TaskList> getAllCategoryTasks(@RequestParam Long id){
        TaskList taskList = new TaskList(taskCategoryService.getAllTasks(id));
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }

    @Operation( summary = "Update Task Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping(value= "/utc/{id}")
    public ResponseEntity<TaskCategoryDTO> updateTask(@RequestBody TaskCategoryMV taskCategoryMV, @PathVariable Long id){
        TaskCategoryDTO updatedTaskCategory = taskCategoryService.update(taskCategoryMV, id);
        return ResponseEntity.created(URI.create("/st/" + updatedTaskCategory.getId())).body(updatedTaskCategory);
    }
}
