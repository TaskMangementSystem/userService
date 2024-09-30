package com.taskManagement.userservice.repository;

import com.taskManagement.userservice.model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepo extends JpaRepository<OrganizationModel, Long> {
    Optional<OrganizationModel> findById(Long id);
}
