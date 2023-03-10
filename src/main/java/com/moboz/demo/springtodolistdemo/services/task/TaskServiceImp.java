package com.moboz.demo.springtodolistdemo.services.task;

import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskCommentDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.converters.entityToDto.TaskEntityToDTO;
import com.moboz.demo.springtodolistdemo.converters.dtoToEntity.TaskDTOtoEntity;
import com.moboz.demo.springtodolistdemo.entities.Task;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskMV;
import com.moboz.demo.springtodolistdemo.repos.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskEntityToDTO taskEntityToDTO;
    private final TaskDTOtoEntity taskDTOtoEntity;

    @Autowired
    public TaskServiceImp(@Lazy TaskRepository taskRepository,
                          @Lazy TaskEntityToDTO taskEntityToDTO,
                          @Lazy TaskDTOtoEntity taskDTOtoEntity) {
        this.taskRepository = taskRepository;
        this.taskEntityToDTO = taskEntityToDTO;
        this.taskDTOtoEntity = taskDTOtoEntity;
    }

    @Override
    public TaskDTO save(TaskMV taskMV) {
        TaskDTO taskDTO = TaskDTO.builder()
                .categoryId(taskMV.getCategoryId())
                .description(taskMV.getDescription())
                .title(taskMV.getTitle())
                .user(taskMV.getUser())
                .due_date(taskMV.getDue_date())
                .build();
        Task task = taskDTOtoEntity.convert(taskDTO);
        task = taskRepository.save(task);
        return taskEntityToDTO.convert(task);
    }

    @Override
    @Transactional
    public List<TaskDTO> listAll() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> tasksDTO = tasks
                .stream()
                .map(taskEntityToDTO::convert)
                .collect(Collectors.toList());
        return tasksDTO;
    }

    @Override
    @Transactional
    public List<TaskCommentDTO> getTaskComments(Long taskId) {
        TaskDTO taskDTO = getDTOById(taskId);
        return taskDTO.getComments();
    }

    @Override
    @Transactional
    public List<TaskAttachmentDTO> getTaskAttachments(Long taskId) {
        TaskDTO taskDTO = getDTOById(taskId);
        return taskDTO.getAttachments();
    }

    @Override
    @Transactional
    public TaskDTO getDTOById(Long id) {
        Task task = findById(id);
        return taskEntityToDTO.convert(task);
    }

    @Override
    @Transactional
    public Task findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TaskDTO update(TaskMV taskMV, Long taskId) {
        TaskDTO taskDTO = getDTOById(taskId);

        Optional.ofNullable(taskMV.getCategoryId()).ifPresent(taskDTO::setCategoryId);
        Optional.ofNullable(taskMV.getDescription()).ifPresent(taskDTO::setDescription);
        Optional.ofNullable(taskMV.getTitle()).ifPresent(taskDTO::setTitle);
        Optional.ofNullable(taskMV.getDue_date()).ifPresent(taskDTO::setDue_date);

        Task task = taskDTOtoEntity.convert(taskDTO);
        task = taskRepository.save(task);
        return taskEntityToDTO.convert(task);

    }
}
