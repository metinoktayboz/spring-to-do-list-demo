package com.moboz.demo.springtodolistdemo.repos;

import com.moboz.demo.springtodolistdemo.entities.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCommentRepository  extends JpaRepository<TaskComment, Long> {
}
