package com.moboz.demo.springtodolistdemo.services.user;

import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.dtos.UserDTO;
import com.moboz.demo.springtodolistdemo.converters.dtoToEntity.UserDTOtoEntity;
import com.moboz.demo.springtodolistdemo.converters.entityToDto.UserEntityToDTO;
import com.moboz.demo.springtodolistdemo.entities.Task;
import com.moboz.demo.springtodolistdemo.entities.Users;
import com.moboz.demo.springtodolistdemo.models.modelView.UserMV;
import com.moboz.demo.springtodolistdemo.repos.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService{

    private final UserDTOtoEntity userDTOtoEntity;
    private final UserRepository userRepository;
    private final UserEntityToDTO userEntityToDTO;

    @Autowired
    public UserServiceImp(@Lazy UserDTOtoEntity userDTOtoEntity,
                          @Lazy UserRepository userRepository,
                          @Lazy UserEntityToDTO userEntityToDTO) {
        this.userDTOtoEntity = userDTOtoEntity;
        this.userRepository = userRepository;
        this.userEntityToDTO = userEntityToDTO;
    }

    @Override
    public UserDTO save(UserMV userMV) {
        UserDTO userDTO = UserDTO.builder()
                .email(userMV.getEmail())
                .firstName(userMV.getFirstName())
                .lastName(userMV.getLastName())
                .build();
        Users user = userDTOtoEntity.convert(userDTO);
        user = userRepository.save(user);
        return userEntityToDTO.convert(user);
    }

    @Override
    @Transactional
    public List<UserDTO> listAll() {
        List<UserDTO> usersDTO = userRepository.findAll()
                .stream()
                .map(userEntityToDTO::convert)
                .collect(Collectors.toList());
        return usersDTO;    }

    @Override
    @Transactional
    public UserDTO getDTOById(Long id) {
        Users user = findById(id);
        return userEntityToDTO.convert(user);
    }

    @Override
    @Transactional
    public Users findById(Long id) {
        Optional<Users> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDTO update(UserMV userMV, Long userId) {
        UserDTO userDTO = getDTOById(userId);

        Optional.ofNullable(userMV.getEmail()).ifPresent(userDTO::setEmail);
        Optional.ofNullable(userMV.getLastName()).ifPresent(userDTO::setLastName);
        Optional.ofNullable(userMV.getFirstName()).ifPresent(userDTO::setFirstName);

        Users user = userDTOtoEntity.convert(userDTO);
        user = userRepository.save(user);
        return userEntityToDTO.convert(user);
    }

    @Override
    @Transactional
    public List<TaskDTO> getAllTasks(Long id) {
        UserDTO userDTO = getDTOById(id);
        return userDTO.getTasks();
    }
}
