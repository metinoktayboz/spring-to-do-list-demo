package com.moboz.demo.springtodolistdemo.repos;

import com.moboz.demo.springtodolistdemo.entities.TaskAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAttachmentRepository extends JpaRepository<TaskAttachment, Long> {
}
