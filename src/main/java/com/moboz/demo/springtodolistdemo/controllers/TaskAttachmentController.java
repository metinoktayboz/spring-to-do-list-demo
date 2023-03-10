package com.moboz.demo.springtodolistdemo.controllers;

import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.models.TaskAttachmentList;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskAttachmentMV;
import com.moboz.demo.springtodolistdemo.services.taskAttachment.TaskAttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/ta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskAttachmentController {
    private final TaskAttachmentService taskAttachmentService;

    @Autowired
    public TaskAttachmentController(TaskAttachmentService taskAttachmentService) {
        this.taskAttachmentService = taskAttachmentService;
    }

    @Operation( summary = "Save Attachment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping(value= "/sa")
    public ResponseEntity<TaskAttachmentDTO> saveAttachment(@ModelAttribute TaskAttachmentMV taskAttachmentMV,
                                                            @RequestParam("file") MultipartFile multipartFile) throws IOException {
        taskAttachmentMV.setData(multipartFile);
        TaskAttachmentDTO savedAttachment = taskAttachmentService.save(taskAttachmentMV);
        return ResponseEntity.created(URI.create("/st/" + savedAttachment.getId())).body(savedAttachment);
    }

    @Operation( summary = "Find by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/fbi")
    public ResponseEntity<TaskAttachmentDTO> findById(@RequestParam Long id){
        TaskAttachmentDTO taskAttachmentDTO = taskAttachmentService.getDTOById(id);
        return ResponseEntity.created(URI.create("/st/" + taskAttachmentDTO.getId())).body(taskAttachmentDTO);
    }

    @Operation( summary = "Delete by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @DeleteMapping(value= "/dbi")
    public ResponseEntity<?> deleteById(@RequestParam Long id){
        taskAttachmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation( summary = "Get All Attachments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/gaa")
    public ResponseEntity<TaskAttachmentList> getAllAttachments(){
        TaskAttachmentList taskCategoryList = new TaskAttachmentList(taskAttachmentService.listAll());
        return new ResponseEntity<>(taskCategoryList, HttpStatus.OK);
    }

    @Operation( summary = "Update Attachment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping(value= "/uta/{id}")
    public ResponseEntity<TaskAttachmentDTO> updateAttachment(@RequestBody TaskAttachmentMV taskAttachmentMV, @PathVariable Long id) throws IOException {
        TaskAttachmentDTO updatedAttachment = taskAttachmentService.update(taskAttachmentMV, id);
        return ResponseEntity.created(URI.create("/st/" + updatedAttachment.getId())).body(updatedAttachment);
    }

}
