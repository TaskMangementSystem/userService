package com.taskManagement.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.taskManagement.userservice.model.UsersModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<UsersModel, Long> {
    UsersModel findByEmail(String email);

    UsersModel findByUsername(String username);
}
