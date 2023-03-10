package com.moboz.demo.springtodolistdemo.models;

import com.moboz.demo.springtodolistdemo.dtos.TaskAttachmentDTO;
import com.moboz.demo.springtodolistdemo.entities.TaskAttachment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskAttachmentList {
    private List<TaskAttachmentDTO> taskAttachments;
}
