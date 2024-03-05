package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserRestController {
	
	@Autowired
    UserService userService;
	
	@PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {

        User userDetails = userService.register(userDTO);;
        return ResponseEntity.ok().body(userDetails);
    }
	@PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO){

        User userDetails = userService.login(loginDTO);
        return ResponseEntity.ok().body(userDetails);

    }

}
