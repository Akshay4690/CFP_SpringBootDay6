package com.demo.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.employee.dto.EmployeeModelDTO;
import com.demo.employee.entity.EmployeeModel;
import com.demo.employee.exception.EmployeeException;
import com.demo.employee.repository.EmployeeRepository;

@Service
public class EmployeeService implements EService {

	@Autowired
	EmployeeRepository repository;
	
	@Override
	public EmployeeModel postempuser(EmployeeModelDTO model) {
		EmployeeModel user = new EmployeeModel (model);
		repository.save(user);
		return user;
	}

	@Override
	public List<EmployeeModel> getAllUser() {
		return repository.findAll();
	}

	@Override
	public EmployeeModel getAllUser(int id) {

		Optional<EmployeeModel> user = repository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		else throw new EmployeeException("Employee not found");

	}

	@Override
	public String deleteUser(int id) {
		Optional<EmployeeModel> user = repository.findById(id);
		if (user.isPresent()) {
			repository.deleteById(id);
			return "User Deleted SuccessFully,for id:" +id;	
		}
		else {
			new EmployeeException("Employee Not Found");
		}
		return null;
	}

	@Override
	public EmployeeModel updateUser(int id, EmployeeModelDTO model) {
		EmployeeModel eModel = repository.findById(id).get();
		if (eModel != null) {
			eModel.setName(model.getName());
			eModel.setGender(model.getGender());
			eModel.setStartDate(model.getStartDate());
			eModel.setSalary(model.getSalary());
			eModel.setProfilePic(model.getProfilePic());
			eModel.setDepartment(model.getDepartment());
			eModel.setNote(model.getNote());
			repository.save(eModel);
			return eModel;	
		}
		else {
			   new EmployeeException("Employee Not Found");
		}
		return null;
		
	}
	
	@Override
	public EmployeeModel getUserByName(String name) {
		Optional<EmployeeModel> user = repository.findByName(name);
		if(user.isPresent()) {
			return user.get();			
		}
		else {
			throw new EmployeeException("Employee not found");
		}
	}

	@Override
	public List<EmployeeModel> getDeptByName(String department) {
		List<EmployeeModel> user = repository.findAllByDepartment(department);
		return user;
	}


}
