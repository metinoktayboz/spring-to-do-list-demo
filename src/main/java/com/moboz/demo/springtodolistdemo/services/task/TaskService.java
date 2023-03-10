package com.moboz.demo.springtodolistdemo.services.task;

import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskCommentDTO;
import com.moboz.demo.springtodolistdemo.dtos.TaskDTO;
import com.moboz.demo.springtodolistdemo.entities.Task;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskMV;

import java.util.List;

public interface TaskService {
    TaskDTO save(TaskMV taskMV);
    List<TaskDTO> listAll();
    List<TaskCommentDTO> getTaskComments(Long taskId);
    List<TaskAttachmentDTO> getTaskAttachments(Long taskId);
    TaskDTO getDTOById(Long id);
    Task findById(Long id);
    void delete(Long id);
    TaskDTO update(TaskMV taskMV, Long taskId);
}
