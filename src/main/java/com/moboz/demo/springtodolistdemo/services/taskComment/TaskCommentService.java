package com.moboz.demo.springtodolistdemo.services.taskComment;

import com.moboz.demo.springtodolistdemo.dtos.TaskCommentDTO;
import com.moboz.demo.springtodolistdemo.entities.TaskComment;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskCommentMV;

import java.util.List;

public interface TaskCommentService {
    TaskCommentDTO save(TaskCommentMV taskCommentMV);
    List<TaskCommentDTO> listAll();
    TaskComment findById(Long id);
    TaskCommentDTO getDTOById(Long id);
    void delete(Long id);
    TaskCommentDTO update(TaskCommentMV taskCommentMV, Long commentId);
}
