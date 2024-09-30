package com.taskManagement.userservice.dto;

public class RegisterDto extends RegisterResponseDto{

    String password;
    Long role_id;
    Long organization_id;

    //There was no need to create a constructor here
    //Added because this class is inherited from RegisterResponseDto
    public RegisterDto(String username, String email, String first_name, String last_name) {
        super(username, email, first_name, last_name);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Long organization_id) {
        this.organization_id = organization_id;
    }
}
