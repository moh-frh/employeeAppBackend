package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapperImpl;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{
	
    UserRepository userRepository;
    UserMapperImpl userMapper;
    
    UserServiceImpl(UserRepository userRepository, UserMapperImpl userMapper){
    	this.userRepository = userRepository;
    	this.userMapper = userMapper;
    }
    
    @Override
    public User register(UserDTO userDTO){
    	System.out.println("register1");

        User user = userMapper.fromUserDTO(userDTO);

        Optional<User> local  = this.userRepository.findByUsername(user.getUsername());
        User current = null;
        if(local.isPresent()) {
        	//throw new LibraryException(ErrorConstant.USER_PRESENT);
        	System.out.println("user exist");
        }
            
        else {
        	System.out.println("register 2");
        	current = this.userRepository.save(user);
        }

        return current;
    }

    @Override
    public User login(LoginDTO loginDTO){

    	// should check for username + password
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(null);
        System.out.println("loginDTO.getUserName(): "+loginDTO.getUsername());
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

}
