package com.taskManagement.userservice.controller;

import com.taskManagement.userservice.dto.OrganizationResponseDto;
import com.taskManagement.userservice.model.OrganizationModel;
import com.taskManagement.userservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Organization {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("createOrganization")
    public ResponseEntity<OrganizationResponseDto> createOrganization (@RequestBody OrganizationResponseDto organizationDto){

        try {
            OrganizationModel organizationModel = organizationService.newOrganization(organizationDto.getName(), organizationDto.getDescription());

            OrganizationResponseDto organizationResponseDto = new OrganizationResponseDto(organizationModel.getName(), organizationModel.getDescription());
            return new ResponseEntity<OrganizationResponseDto>(organizationResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
