package com.taskManagement.userservice.security;

import com.taskManagement.userservice.model.UsersModel;
import com.taskManagement.userservice.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UsersRepo usersRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersModel usersModel = usersRepo.findByUsername(username);
        return new User(usersModel.getUsername(), usersModel.getPassword(), new ArrayList<>());
    }
}
