package com.moboz.demo.springtodolistdemo.services.user;

import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.dtos.UserDTO;
import com.moboz.demo.springtodolistdemo.entities.Users;
import com.moboz.demo.springtodolistdemo.models.modelView.UserMV;

import java.util.List;

public interface UserService {
    UserDTO save(UserMV userMV);
    List<UserDTO> listAll();
    UserDTO getDTOById(Long id);
    Users findById(Long id);
    void delete(Long id);
    UserDTO update(UserMV userMV, Long userId);
    List<TaskDTO> getAllTasks(Long id);
}
