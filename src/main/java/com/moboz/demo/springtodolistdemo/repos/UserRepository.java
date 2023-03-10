package com.moboz.demo.springtodolistdemo.repos;

import com.moboz.demo.springtodolistdemo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
