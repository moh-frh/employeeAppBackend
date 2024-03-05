package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.EmployeeNotFoundException;


public interface UserService {
	public User register(UserDTO userDTO);

    public User login(LoginDTO loginDTO);

    public Optional<User> findByUsername(String username);

}
