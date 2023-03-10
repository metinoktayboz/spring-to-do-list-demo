package com.moboz.demo.springtodolistdemo.controllers;

import com.moboz.demo.springtodolistdemo.dtos.TaskCommentDTO;
import com.moboz.demo.springtodolistdemo.models.TaskCommentList;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskCommentMV;
import com.moboz.demo.springtodolistdemo.services.taskComment.TaskCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/tcoc")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskCommentController {

    private final TaskCommentService taskCommentService;

    @Autowired
    public TaskCommentController(TaskCommentService taskCommentService) {
        this.taskCommentService = taskCommentService;
    }

    @Operation( summary = "Save Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping(value= "/sc")
    public ResponseEntity<TaskCommentDTO> saveComment(@RequestBody TaskCommentMV taskCommentMV){
        TaskCommentDTO savedComment = taskCommentService.save(taskCommentMV);
        return ResponseEntity.created(URI.create("/st/" + savedComment.getId())).body(savedComment);
    }

    @Operation( summary = "Find by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/fbi")
    public ResponseEntity<TaskCommentDTO> findById(@RequestParam Long id){
        TaskCommentDTO taskCommentDTO = taskCommentService.getDTOById(id);
        return ResponseEntity.created(URI.create("/st/" + taskCommentDTO.getId())).body(taskCommentDTO);
    }

    @Operation( summary = "Delete by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @DeleteMapping(value= "/dbi")
    public ResponseEntity<?> deleteById(@RequestParam Long id){
        taskCommentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation( summary = "Get All Comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/gac")
    public ResponseEntity<TaskCommentList> getAllComments(){
        TaskCommentList taskCommentList = new TaskCommentList(taskCommentService.listAll());
        return new ResponseEntity<>(taskCommentList, HttpStatus.OK);
    }

    @Operation( summary = "Update Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping(value= "/utc/{id}")
    public ResponseEntity<TaskCommentDTO> updateComment(@RequestBody TaskCommentMV taskCommentMV, @PathVariable Long id){
        TaskCommentDTO updatedComment= taskCommentService.update(taskCommentMV, id);
        return ResponseEntity.created(URI.create("/st/" + updatedComment.getId())).body(updatedComment);
    }
}
