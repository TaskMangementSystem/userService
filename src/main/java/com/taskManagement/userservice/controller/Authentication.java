package com.taskManagement.userservice.controller;

import com.taskManagement.userservice.dto.LoginDto;
import com.taskManagement.userservice.dto.LoginResponseDto;
import com.taskManagement.userservice.dto.RegisterDto;
import com.taskManagement.userservice.dto.RegisterResponseDto;
import com.taskManagement.userservice.model.UsersModel;
import com.taskManagement.userservice.repository.UsersRepo;
import com.taskManagement.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class Authentication {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsersRepo usersRepo;

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto registerDto){

        try{
            UsersModel usersModel = authService.registerUser(registerDto.getUsername(),
                    registerDto.getEmail(),
                    registerDto.getPassword(),
                    registerDto.getFirst_name(),
                    registerDto.getLast_name(),
                    registerDto.getRole_id(),
                    registerDto.getOrganization_id());

            RegisterResponseDto registerResponseDto = new RegisterResponseDto(usersModel.getUsername(),
                    usersModel.getEmail(),
                    usersModel.getFirst_name(),
                    usersModel.getLast_name());

            return new ResponseEntity<RegisterResponseDto>(registerDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto logindto){

        try{
            String jwt = authService.loginUserByUserName(logindto.getUsername(), logindto.getPassword());

            if (jwt != null){
                UsersModel usersModel = usersRepo.findByUsername(logindto.getUsername());
                LoginResponseDto loginResponseDto = new LoginResponseDto(jwt,
                        usersModel.getUsername(),
                        usersModel.getEmail(),
                        usersModel.getFirst_name(),
                        usersModel.getLast_name());

                return new ResponseEntity<LoginResponseDto>(loginResponseDto, HttpStatus.OK);
            }

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
