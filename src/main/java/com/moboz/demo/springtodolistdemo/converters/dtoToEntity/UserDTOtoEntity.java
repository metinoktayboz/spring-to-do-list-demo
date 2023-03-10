package com.moboz.demo.springtodolistdemo.converters.dtoToEntity;

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
public class UserDTOtoEntity implements Converter<UserDTO, Users> {

    private final TaskDTOtoEntity taskDTOtoEntity;

    @Autowired
    public UserDTOtoEntity(@Lazy TaskDTOtoEntity taskDTOtoEntity) {
        this.taskDTOtoEntity = taskDTOtoEntity;
    }

    @Override
    public Users convert(UserDTO userDTO) {
        List<TaskDTO> taskDTOS = userDTO.getTasks();
        List<Task> tasks = taskDTOS != null
                ? taskDTOS.stream()
                .map(taskDTOtoEntity::convert)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return Users.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .tasks(tasks)
                .build();
    }
}
