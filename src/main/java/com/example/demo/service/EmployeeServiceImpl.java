package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.mapper.EmployeeMapperImpl;
import com.example.demo.repository.EmployeeRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{
	EmployeeRepository employeeRepository;
	EmployeeMapperImpl dtoMapperEmployee;
    
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapperImpl dtoMapperEmployee){
        this.employeeRepository = employeeRepository;
        this.dtoMapperEmployee = dtoMapperEmployee;
    }
    @Override
    public List<EmployeeDTO> listEmployees(){
        List<Employee> listEmployees = employeeRepository.findAll();
        List<EmployeeDTO> listEmployeeDTOS = new ArrayList<>();
        for(Employee employee:listEmployees){
        	EmployeeDTO employeeDTO = dtoMapperEmployee.fromEmployee(employee);
            listEmployeeDTOS.add(employeeDTO);
        }
        return listEmployeeDTOS;
    }
    
    @Override
    public EmployeeDTO getEmployeeById(Long id) throws EmployeeNotFoundException {
    	Employee employee = employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException("Employee not found !!!!"));
        return dtoMapperEmployee.fromEmployee(employee);
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
    	Employee employee = dtoMapperEmployee.fromEmployeeDTO(employeeDTO);
        employee.setCreatedAt(new Date());
        employee.setUpdatedAt(new Date());
        Employee savedEmployee = employeeRepository.save(employee);
        return dtoMapperEmployee.fromEmployee(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws EmployeeNotFoundException {
        //log.info("updating new user !!");
        Employee userInformation = employeeRepository.findById(employeeDTO.getId()).orElseThrow(()->new EmployeeNotFoundException("employee not found !!!"));
        Employee user = dtoMapperEmployee.fromEmployeeDTO(employeeDTO);
        user.setCreatedAt(userInformation.getCreatedAt());
        user.setUpdatedAt(new Date());
        Employee savedEmployee = employeeRepository.save(user);
        return dtoMapperEmployee.fromEmployee(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long id){
    	employeeRepository.deleteById(id);
    }
	@Override 
	public List<EmployeeDTO> searchEmployees(String keyword) {
		List<Employee> listEmployees = employeeRepository.findByFirstNameContains(keyword);
        List<EmployeeDTO> listEmployeeDTOS = new ArrayList<>();
        for(Employee employee:listEmployees){
        	EmployeeDTO employeeDTO = dtoMapperEmployee.fromEmployee(employee);
            listEmployeeDTOS.add(employeeDTO);
        }
        return listEmployeeDTOS;
	}
}
