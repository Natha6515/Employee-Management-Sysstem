package com.student.student_crud.services;

import java.util.List;

import com.student.student_crud.model.EmployeeModel;

public interface EmployeeService {

	EmployeeModel insertEmployee(EmployeeModel employee);

	List<EmployeeModel> getAllEmployees();

	boolean deleteEmployee(long id);

	EmployeeModel getEmployeeNyId(long id);

	boolean updateEmployee(long id, EmployeeModel employeeModel);

	List<EmployeeModel> searchData(String data);
	
	

}
