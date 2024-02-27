package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.exception.EmployeeNotFoundException;

public interface EmployeeService {
	public List<EmployeeDTO> listEmployees();
	
	public EmployeeDTO getEmployeeById(Long id) throws EmployeeNotFoundException;;

	EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

	EmployeeDTO updateEmployee(EmployeeDTO employeeDTO)  throws EmployeeNotFoundException;

    void deleteEmployee(Long id);

	public List<EmployeeDTO> searchEmployees(String keyword);
}
