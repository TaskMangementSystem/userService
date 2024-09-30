package com.taskManagement.userservice.service;

import com.taskManagement.userservice.model.OrganizationModel;
import com.taskManagement.userservice.repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepo organizationRepo;

    public OrganizationModel newOrganization(String name, String description){
        OrganizationModel organizationModel = new OrganizationModel();
        organizationModel.setName(name);
        organizationModel.setDescription(description);

        organizationRepo.save(organizationModel);

        return organizationModel;
    }
}
