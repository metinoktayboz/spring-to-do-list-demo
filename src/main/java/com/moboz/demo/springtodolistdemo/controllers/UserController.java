package com.moboz.demo.springtodolistdemo.controllers;

import com.moboz.demo.springtodolistdemo.dtos.UserDTO;
import com.moboz.demo.springtodolistdemo.models.TaskList;
import com.moboz.demo.springtodolistdemo.models.UserList;
import com.moboz.demo.springtodolistdemo.models.modelView.UserMV;
import com.moboz.demo.springtodolistdemo.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/uc")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation( summary = "Save User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping(value= "/su")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserMV userMV){
        UserDTO savedUser = userService.save(userMV);
        return ResponseEntity.created(URI.create("/st/" + savedUser.getId())).body(savedUser);
    }

    @Operation( summary = "Find by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/fbi")
    public ResponseEntity<UserDTO> findById(@RequestParam Long id){
        UserDTO userDTO = userService.getDTOById(id);
        return ResponseEntity.created(URI.create("/st/" + userDTO.getId())).body(userDTO);
    }

    @Operation( summary = "Delete by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @DeleteMapping(value= "/dbi")
    public ResponseEntity<?> deleteById(@RequestParam Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation( summary = "Get All Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/gau")
    public ResponseEntity<UserList> getAllTasks(){
        UserList userList = new UserList(userService.listAll());
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @Operation( summary = "Update User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @PostMapping(value= "/uu/{id}")
    public ResponseEntity<UserDTO> updateTask(@RequestBody UserMV userMV, @PathVariable Long id){
        UserDTO updatedUser = userService.update(userMV, id);
        return ResponseEntity.created(URI.create("/st/" + updatedUser.getId())).body(updatedUser);
    }

    @Operation( summary = "Get User Tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "bad request")
    })
    @GetMapping(value= "/gut/{id}")
    public ResponseEntity<TaskList> getComments(@PathVariable Long id){
        TaskList taskList = new TaskList(userService.getAllTasks(id));
        return new ResponseEntity<>(taskList, HttpStatus.OK);
    }
}
