package com.moboz.demo.springtodolistdemo.repos;

import com.moboz.demo.springtodolistdemo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
