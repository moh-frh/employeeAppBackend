package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeRestController {
	private EmployeeService employeeService;
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> listEmployees(){
        return employeeService.listEmployees();
    }
    
    @GetMapping("/employees/search")
    public List<EmployeeDTO> searchEmployees(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return employeeService.searchEmployees(keyword);
    }
    
    @GetMapping("/employees/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "id") Long userId) throws EmployeeNotFoundException {
        return employeeService.getEmployeeById(userId);
    }

    @PostMapping("/employees")
    public EmployeeDTO saveUser(@RequestBody EmployeeDTO userDTO){
        return employeeService.saveEmployee(userDTO);
    }

    @PutMapping("/employees/{id}")
    public EmployeeDTO updateEmployee(@PathVariable(name = "id") Long userId, @RequestBody EmployeeDTO employeeDTO) throws EmployeeNotFoundException {
    	employeeDTO.setId(userId);
        return employeeService.updateEmployee(employeeDTO);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteUser(@PathVariable(name = "id") Long employeeID){
    	employeeService.deleteEmployee(employeeID);
    }
}
