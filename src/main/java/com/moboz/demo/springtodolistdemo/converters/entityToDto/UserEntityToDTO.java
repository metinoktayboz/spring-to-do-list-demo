package com.moboz.demo.springtodolistdemo.converters.entityToDto;

import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.dtos.UserDTO;
import com.moboz.demo.springtodolistdemo.entities.Task;
import com.moboz.demo.springtodolistdemo.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityToDTO implements Converter<Users, UserDTO>{

    private final TaskEntityToDTO taskEntityToDTO;

    @Autowired
    public UserEntityToDTO(@Lazy TaskEntityToDTO taskEntityToDTO) {
        this.taskEntityToDTO = taskEntityToDTO;
    }

    @Override
    public UserDTO convert(Users user) {

        List<Task> tasks = user.getTasks();
        List<TaskDTO> tasksDTO = tasks != null
                ? tasks.stream()
                .map(taskEntityToDTO::convert)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .tasks(tasksDTO)
                .build();
    }
}
