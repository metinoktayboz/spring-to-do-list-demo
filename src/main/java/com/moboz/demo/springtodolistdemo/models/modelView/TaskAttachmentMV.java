package com.moboz.demo.springtodolistdemo.models.modelView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskAttachmentMV {
    private Long taskId;
    private String fileName;
    private String contentType;
    private MultipartFile data;
}
