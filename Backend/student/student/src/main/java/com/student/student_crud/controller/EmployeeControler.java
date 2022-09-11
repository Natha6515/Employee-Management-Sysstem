package com.student.student_crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.student_crud.model.EmployeeModel;
import com.student.student_crud.services.EmployeeService;

@CrossOrigin(origins = {"http://localhost:3000" , "http://localhost:3001"} , methods = {RequestMethod.GET ,
RequestMethod.POST , RequestMethod.PUT , RequestMethod.DELETE , RequestMethod.PATCH})
@RestController
@RequestMapping("/api/v1/")
public class EmployeeControler {

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	@Autowired
	private final EmployeeService employeeService;

	public EmployeeControler(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@PostMapping(value = "/employee")
	public EmployeeModel createEmployee(@RequestBody EmployeeModel employee) {
		return employeeService.insertEmployee(employee);
	}
	
	@GetMapping(value = "/employee")
	public List<EmployeeModel> getAllEmployee(){
		List<EmployeeModel> ls = employeeService.getAllEmployees();
		return ls;
	}
	
	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmploye(@PathVariable long id){
		boolean isdeleted = false;
		isdeleted = employeeService.deleteEmployee(id);
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("deleted", isdeleted);
		return ResponseEntity.ok(map);
	}

	@GetMapping(value = "/employee/{id}")
	public EmployeeModel getEmployee(@PathVariable long id) {
		EmployeeModel em = employeeService.getEmployeeNyId(id);
		return em;
	}
	
	@PutMapping(value = "/employee/{id}")
	public ResponseEntity<Map<String, Boolean>> updateEmployee(@PathVariable long id , @RequestBody EmployeeModel employeeModel){
		
		boolean isupdated = false;
		
		employeeModel.setId(id);
		isupdated = employeeService.updateEmployee(id , employeeModel);
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("updated", isupdated);
		return ResponseEntity.ok(map);
	}
	
	@PatchMapping(value = "/employee/{data}")
	public List<EmployeeModel> searchData(@PathVariable String data){
		
		List<EmployeeModel> datalist = employeeService.searchData(data);
		return datalist;
	}
}
