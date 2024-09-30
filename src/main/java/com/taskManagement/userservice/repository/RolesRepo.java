package com.taskManagement.userservice.repository;

import com.taskManagement.userservice.model.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepo extends JpaRepository<RolesModel, Long> {
    Optional<RolesModel> findById(Long id);
}
