package com.moboz.demo.springtodolistdemo.services.taskComment;

import com.moboz.demo.springtodolistdemo.converters.dtoToEntity.TaskCommentDTOtoEntity;
import com.moboz.demo.springtodolistdemo.converters.entityToDto.TaskCommentEntityToDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskCommentDTO;
import com.moboz.demo.springtodolistdemo.entities.TaskComment;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskCommentMV;
import com.moboz.demo.springtodolistdemo.repos.TaskCommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskCommentServiceImp implements TaskCommentService{

    private final TaskCommentDTOtoEntity taskCommentDTOtoEntity;
    private final TaskCommentEntityToDTO taskCommentEntityToDTO;
    private final TaskCommentRepository taskCommentRepository;

    @Autowired
    public TaskCommentServiceImp(@Lazy TaskCommentDTOtoEntity taskCommentDTOtoEntity,
                                 @Lazy TaskCommentEntityToDTO taskCommentEntityToDTO,
                                 @Lazy TaskCommentRepository taskCommentRepository) {
        this.taskCommentDTOtoEntity = taskCommentDTOtoEntity;
        this.taskCommentEntityToDTO = taskCommentEntityToDTO;
        this.taskCommentRepository = taskCommentRepository;
    }


    @Override
    public TaskCommentDTO save(TaskCommentMV taskCommentMV) {
        TaskCommentDTO taskCommentDTO = TaskCommentDTO.builder()
                .commentText(taskCommentMV.getCommentText())
                .taskId(taskCommentMV.getTaskId())
                .createdAt(taskCommentMV.getCreatedAt())
                .userId(taskCommentMV.getUserId())
                .build();

        TaskComment taskComment = taskCommentDTOtoEntity.convert(taskCommentDTO);
        taskComment = taskCommentRepository.save(taskComment);
        return taskCommentEntityToDTO.convert(taskComment);
    }

    @Override
    @Transactional
    public List<TaskCommentDTO> listAll() {
        List<TaskComment> taskComments = taskCommentRepository.findAll();
        List<TaskCommentDTO> taskCommenstDTO = taskComments
                .stream()
                .map(taskCommentEntityToDTO::convert)
                .collect(Collectors.toList());
        return taskCommenstDTO;
    }

    @Override
    @Transactional
    public TaskComment findById(Long id) {
        Optional<TaskComment> isValid = taskCommentRepository.findById(id);
        TaskComment taskComment = new TaskComment();
        if(isValid.isPresent()){
            taskComment = isValid.get();
        }
        return taskComment;
    }

    @Override
    @Transactional
    public TaskCommentDTO getDTOById(Long id) {
        TaskComment taskComment = findById(id);
        return taskCommentEntityToDTO.convert(taskComment);
    }

    @Override
    public void delete(Long id) {
        taskCommentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TaskCommentDTO update(TaskCommentMV taskCommentMV, Long commentId) {
        TaskCommentDTO taskCommentDTO = getDTOById(commentId);

        Optional.ofNullable(taskCommentMV.getCommentText()).ifPresent(taskCommentDTO::setCommentText);
        Optional.ofNullable(taskCommentMV.getTaskId()).ifPresent(taskCommentDTO::setTaskId);
        Optional.ofNullable(taskCommentMV.getCreatedAt()).ifPresent(taskCommentDTO::setCreatedAt);
        Optional.ofNullable(taskCommentMV.getUserId()).ifPresent(taskCommentDTO::setUserId);

        TaskComment taskComment = taskCommentDTOtoEntity.convert(taskCommentDTO);
        taskComment = taskCommentRepository.save(taskComment);
        return taskCommentEntityToDTO.convert(taskComment);
    }
}
