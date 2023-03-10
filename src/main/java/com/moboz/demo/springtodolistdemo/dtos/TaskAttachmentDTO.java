package com.moboz.demo.springtodolistdemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskAttachmentDTO {
    private Long id;
    private Long taskId;
    private String fileName;
    private String contentType;
    private byte[] data;
}
