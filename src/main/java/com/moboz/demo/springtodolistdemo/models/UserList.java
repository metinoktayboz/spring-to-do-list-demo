package com.moboz.demo.springtodolistdemo.models;

import com.moboz.demo.springtodolistdemo.dtos.UserDTO;
import com.moboz.demo.springtodolistdemo.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserList {
    private List<UserDTO> userList;
}
