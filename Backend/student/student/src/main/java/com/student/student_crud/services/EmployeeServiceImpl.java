package com.student.student_crud.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.student_crud.entity.EmployeeEntity;
import com.student.student_crud.model.EmployeeModel;
import com.student.student_crud.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeModel insertEmployee(EmployeeModel employee) {

		EmployeeEntity ee = new EmployeeEntity();

		BeanUtils.copyProperties(employee, ee);
		employeeRepository.save(ee);
		employee.setId(ee.getId());
		return employee;
	}

	@Override
	public List<EmployeeModel> getAllEmployees() {

		List<EmployeeEntity> employee_list = employeeRepository.findAll();

		List<EmployeeModel> employees = employee_list.stream()
				.map(emp -> new EmployeeModel(emp.getId(), emp.getFirstname(), emp.getLastname(), emp.getEmail()))
				.collect(Collectors.toList());

		return employees;
	}

	@Override
	public boolean deleteEmployee(long id) {

		EmployeeEntity ee = employeeRepository.findById(id).get();
		employeeRepository.delete(ee);
		return true;
	}

	@Override
	public EmployeeModel getEmployeeNyId(long id) {
		EmployeeEntity en = employeeRepository.findById(id).get();
		EmployeeModel em = new EmployeeModel();
		BeanUtils.copyProperties(en, em);
		return em;
	}

	@Override
	public boolean updateEmployee(long id, EmployeeModel employeeModel) {
		EmployeeEntity en = employeeRepository.findById(id).get();
		BeanUtils.copyProperties(employeeModel, en);
		employeeRepository.save(en);
		return true;
	}

	@Override
	public List<EmployeeModel> searchData(String data) {

		String data1 = data.toLowerCase();
		
		List<EmployeeModel> entitylist = new ArrayList<EmployeeModel>();
		List<EmployeeEntity> elist = employeeRepository.findAll();

		entitylist = elist.stream()
				.map(emp -> new EmployeeModel(emp.getId(), emp.getFirstname(), emp.getLastname(), emp.getEmail()))
				.collect(Collectors.toList());

		List<EmployeeModel> result_list = new ArrayList<EmployeeModel>();
		
		Iterator<EmployeeModel> itr = entitylist.iterator();
		
		while (itr.hasNext()) {
			EmployeeModel em = (EmployeeModel)itr.next();
			
			if(em.getFirstname().toLowerCase().contains(data1) || em.getLastname().toLowerCase().contains(data1) 
					|| em.getEmail().toLowerCase().contains(data1)) {
				result_list.add(em);
			}
		}
		
		return result_list;
	}

}
