package com.example.demo.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;

@Service
public class EmployeeMapperImpl {
	public Employee fromEmployeeDTO(EmployeeDTO employeeDTO){
		Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    public EmployeeDTO fromEmployee(Employee employee){
    	EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }
}
