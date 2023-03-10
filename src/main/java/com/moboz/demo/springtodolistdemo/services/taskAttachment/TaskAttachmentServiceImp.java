package com.moboz.demo.springtodolistdemo.services.taskAttachment;

import com.moboz.demo.springtodolistdemo.converters.dtoToEntity.TaskAttachmentDTOtoEntity;
import com.moboz.demo.springtodolistdemo.converters.entityToDto.TaskAttachmentEntityToDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.entities.TaskAttachment;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskAttachmentMV;
import com.moboz.demo.springtodolistdemo.repos.TaskAttachmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskAttachmentServiceImp implements TaskAttachmentService {

    private final TaskAttachmentRepository taskAttachmentRepository;
    private final TaskAttachmentEntityToDTO taskAttachmentEntityToDTO;
    private final TaskAttachmentDTOtoEntity taskAttachmentDTOtoEntity;

    @Autowired
    public TaskAttachmentServiceImp(@Lazy TaskAttachmentRepository taskAttachmentRepository,
                                    @Lazy TaskAttachmentEntityToDTO taskAttachmentEntityToDTO,
                                    @Lazy TaskAttachmentDTOtoEntity taskAttachmentDTOtoEntity) {
        this.taskAttachmentRepository = taskAttachmentRepository;
        this.taskAttachmentEntityToDTO = taskAttachmentEntityToDTO;
        this.taskAttachmentDTOtoEntity = taskAttachmentDTOtoEntity;
    }

    @Override
    public TaskAttachmentDTO save(TaskAttachmentMV taskAttachmentMV) throws IOException {
        TaskAttachmentDTO taskAttachmentDTO = TaskAttachmentDTO.builder()
                .taskId(taskAttachmentMV.getTaskId())
                .contentType(taskAttachmentMV.getContentType())
                .fileName(taskAttachmentMV.getFileName())
                .build();
        if (taskAttachmentMV.getData()!=null){
            taskAttachmentDTO.setData(taskAttachmentMV.getData().getBytes());
        }

        TaskAttachment taskAttachment = taskAttachmentDTOtoEntity.convert(taskAttachmentDTO);
        taskAttachment = taskAttachmentRepository.save(taskAttachment);
        return taskAttachmentEntityToDTO.convert(taskAttachment);
    }

    @Override
    @Transactional
    public List<TaskAttachmentDTO> listAll() {
        List<TaskAttachment> taskAttachments = taskAttachmentRepository.findAll();
        List<TaskAttachmentDTO> taskAttachmentsDTO = taskAttachments
                .stream()
                .map(taskAttachmentEntityToDTO::convert)
                .collect(Collectors.toList());
        return taskAttachmentsDTO;
    }

    @Override
    @Transactional
    public TaskAttachmentDTO getDTOById(Long id) {
        TaskAttachment taskAttachment = findById(id);
        return taskAttachmentEntityToDTO.convert(taskAttachment);
    }

    @Override
    @Transactional
    public TaskAttachment findById(Long id) {
        Optional<TaskAttachment> taskAttachment = taskAttachmentRepository.findById(id);
        return taskAttachment.orElse(null);
    }

    @Override
    public void delete(Long id) {
        taskAttachmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TaskAttachmentDTO update(TaskAttachmentMV taskAttachmentMV, Long categoryId) throws IOException {
        TaskAttachmentDTO taskAttachmentDTO = getDTOById(categoryId);

        Optional.ofNullable(taskAttachmentMV.getTaskId()).ifPresent(taskAttachmentDTO::setTaskId);
        if (taskAttachmentMV.getData()!=null){
            taskAttachmentDTO.setData(taskAttachmentMV.getData().getBytes());
        }
        Optional.ofNullable(taskAttachmentMV.getFileName()).ifPresent(taskAttachmentDTO::setFileName);
        Optional.ofNullable(taskAttachmentMV.getContentType()).ifPresent(taskAttachmentDTO::setContentType);

        TaskAttachment taskAttachment = taskAttachmentDTOtoEntity.convert(taskAttachmentDTO);
        taskAttachment = taskAttachmentRepository.save(taskAttachment);
        return taskAttachmentEntityToDTO.convert(taskAttachment);
    }
}
