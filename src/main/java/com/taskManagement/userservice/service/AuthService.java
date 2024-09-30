package com.taskManagement.userservice.service;

import com.taskManagement.userservice.model.OrganizationModel;
import com.taskManagement.userservice.model.RolesModel;
import com.taskManagement.userservice.model.UsersModel;
import com.taskManagement.userservice.repository.OrganizationRepo;
import com.taskManagement.userservice.repository.RolesRepo;
import com.taskManagement.userservice.repository.UsersRepo;
import com.taskManagement.userservice.security.CustomUserDetailService;
import com.taskManagement.userservice.security.JwtUtil;
import com.taskManagement.userservice.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private RolesRepo rolesRepo;

    public UsersModel registerUser(String username, String email, String password, String firstName, String lastName, Long roles_id, Long organization_id) {
        // Check if email or username already exists
        if (usersRepo.findByEmail(email) != null) {
            throw new IllegalStateException("Email already in use");
        }
        if (usersRepo.findByUsername(username) != null) {
            throw new IllegalStateException("Username already taken");
        }

        Optional<RolesModel> rolesModel = rolesRepo.findById(roles_id);
        Optional<OrganizationModel> organizationModel = organizationRepo.findById(organization_id);


        UsersModel newUser = new UsersModel();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setFirst_name(firstName);
        newUser.setLast_name(lastName);
        newUser.setPassword(passwordEncoder.encode(password)); // Encrypt the password

        if(rolesModel.isPresent()){
            try {
                newUser.setRole(rolesModel.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if(organizationModel.isPresent()){
            try {
                newUser.setOrganization(organizationModel.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return usersRepo.save(newUser);
    }



    public String loginUserByUserName(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));


        } catch (Exception e) {

            return null;
        }

        final UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
        final UsersModel usersModel = usersRepo.findByUsername(username);

        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), usersModel.getRole().getRole_id(), usersModel.getOrganization().getOrganization_id());

        return jwt;
    }
}
