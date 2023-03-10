package com.moboz.demo.springtodolistdemo.services.taskAttachment;

import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.entities.TaskAttachment;
import com.moboz.demo.springtodolistdemo.models.modelView.TaskAttachmentMV;

import java.io.IOException;
import java.util.List;

public interface TaskAttachmentService {
    TaskAttachmentDTO save(TaskAttachmentMV taskAttachmentMV) throws IOException;
    List<TaskAttachmentDTO> listAll();
    TaskAttachmentDTO getDTOById(Long id);
    TaskAttachment findById(Long id);
    void delete(Long id);
    TaskAttachmentDTO update(TaskAttachmentMV taskAttachmentMV, Long categoryId) throws IOException;
}
