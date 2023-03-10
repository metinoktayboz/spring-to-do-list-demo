package com.moboz.demo.springtodolistdemo.repos;

import com.moboz.demo.springtodolistdemo.entities.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
}
