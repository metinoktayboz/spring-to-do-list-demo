package com.moboz.demo.springtodolistdemo.services.taskCategory;

import com.moboz.demo.springtodolistdemo.dtos.TaskCategoryDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.converters.dtoToEntity.TaskCategoryDTOtoEntity;
import com.moboz.demo.springtodolistdemo.converters.entityToDto.TaskCategoryEntityToDTO;
import com.moboz.demo.springtodolistdemo.converters.entityToDto.TaskEntityToDTO;
import com.moboz.demo.springtodolistdemo.entities.Task;
import com.moboz.demo.springtodolistdemo.entities.TaskCategory;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskCategoryMV;
import com.moboz.demo.springtodolistdemo.repos.TaskCategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskCategoryServiceImp implements TaskCategoryService{

    private final TaskCategoryRepository taskCategoryRepository;
    private final TaskEntityToDTO taskEntityToDTO;
    private final TaskCategoryEntityToDTO taskCategoryEntityToDTO;
    private final TaskCategoryDTOtoEntity taskCategoryDTOtoEntity;

    @Autowired
    public TaskCategoryServiceImp(@Lazy TaskCategoryRepository taskCategoryRepository,
                                  @Lazy TaskEntityToDTO taskEntityToDTO,
                                  @Lazy TaskCategoryEntityToDTO taskCategoryEntityToDTO,
                                  @Lazy TaskCategoryDTOtoEntity taskCategoryDTOtoEntity) {
        this.taskCategoryRepository = taskCategoryRepository;
        this.taskEntityToDTO = taskEntityToDTO;
        this.taskCategoryEntityToDTO = taskCategoryEntityToDTO;
        this.taskCategoryDTOtoEntity = taskCategoryDTOtoEntity;
    }

    @Override
    public TaskCategoryDTO save(TaskCategoryMV taskCategoryMV) {
        TaskCategoryDTO taskCategoryDTO = TaskCategoryDTO.builder()
                .categoryName(taskCategoryMV.getCategoryName())
                .build();
        TaskCategory taskCategory = taskCategoryDTOtoEntity.convert(taskCategoryDTO);
        taskCategory = taskCategoryRepository.save(taskCategory);
        return taskCategoryEntityToDTO.convert(taskCategory);
    }

    @Override
    @Transactional
    public List<TaskCategoryDTO> listAll() {
        List<TaskCategory> taskCategories = taskCategoryRepository.findAll();
        List<TaskCategoryDTO> taskCategoriesDTO = taskCategories
                .stream()
                .map(taskCategoryEntityToDTO::convert)
                .collect(Collectors.toList());
        return taskCategoriesDTO;
    }

    @Override
    @Transactional
    public TaskCategory findById(Long id) {
        Optional<TaskCategory> isValid = taskCategoryRepository.findById(id);
        TaskCategory taskCategory = new TaskCategory();
        if(isValid.isPresent()){
            taskCategory = isValid.get();
        }
        return taskCategory;
    }

    @Override
    @Transactional
    public TaskCategoryDTO getDTOById(Long id) {
        TaskCategory taskCategory = findById(id);
        return taskCategoryEntityToDTO.convert(taskCategory);
    }

    @Override
    @Transactional
    public List<TaskDTO> getAllTasks(Long id) {
            TaskCategory taskCategory = findById(id);
            List<Task> tasks = taskCategory.getTasks();
            List<TaskDTO> taskDTOs = new ArrayList<>();
            for (Task task : tasks){
                taskDTOs.add(taskEntityToDTO.convert(task));
            }
            TaskCategoryDTO taskCategoryDTO = taskCategoryEntityToDTO.convert(taskCategory);
            taskCategoryDTO.setTasks(taskDTOs);
            return taskCategoryDTO.getTasks();
    }

    @Override
    public void delete(Long id) {
        taskCategoryRepository.deleteById(id);
    }

    @Override
    public TaskCategoryDTO update(TaskCategoryMV taskCategoryMV, Long categoryId) {
        TaskCategoryDTO taskCategoryDTO = getDTOById(categoryId);

        Optional.ofNullable(taskCategoryMV.getCategoryName()).ifPresent(taskCategoryDTO::setCategoryName);

        TaskCategory taskCategory = taskCategoryDTOtoEntity.convert(taskCategoryDTO);
        taskCategory = taskCategoryRepository.save(taskCategory);
        return taskCategoryEntityToDTO.convert(taskCategory);
    }
}
