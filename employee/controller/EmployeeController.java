package com.demo.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee.dto.EmployeeModelDTO;
import com.demo.employee.dto.responseDTO;
import com.demo.employee.entity.EmployeeModel;
import com.demo.employee.service.EService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	
	@Autowired
	EService service;
	
	@RequestMapping(value = "/search" , method = RequestMethod.GET)
	@ResponseBody
	public String Say()
	{
		return "Hello Spring";
	}
	
	@PostMapping("/add")
	public ResponseEntity<responseDTO> postEmp(@Valid @RequestBody EmployeeModelDTO model)
	{
		EmployeeModel user = service.postempuser(model);
		responseDTO response = new responseDTO(user,"User added Successfully");
		return new ResponseEntity<responseDTO>(response,HttpStatus.CREATED) ;
	}

	@GetMapping("/getAll")
	public ResponseEntity <responseDTO> getAll()
	{
		List<EmployeeModel> allUser = service.getAllUser();
		responseDTO response = new responseDTO(allUser,"All User list of employee ");
		return new ResponseEntity <responseDTO>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<responseDTO> getEmp(@PathVariable int id)
	{
		EmployeeModel User = service.getAllUser(id);
		responseDTO response = new responseDTO(User,"employee get by id Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<responseDTO> deleteEmp(@PathVariable int id)
	{
		String user = service.deleteUser(id);
		responseDTO response = new responseDTO(user,"User delete successfull");
		return new ResponseEntity<responseDTO> (response,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/updateById/{id}")
	public ResponseEntity<responseDTO > putEmp(@PathVariable int id,@Valid @RequestBody EmployeeModelDTO model)
	{
		EmployeeModel user = service.updateUser(id,model);
		responseDTO response = new responseDTO(user,"User updated Successfully");
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<responseDTO> getName(@PathVariable String name)
	{
		EmployeeModel user = service.getUserByName(name);
		responseDTO response = new responseDTO(user,"employee get by name Successfully");
		return new ResponseEntity<responseDTO> (response,HttpStatus.OK);
	}

	@GetMapping("/getByDepartment/{department}")
	public ResponseEntity<responseDTO> getDeptByName (@PathVariable String department )
	{
		List<EmployeeModel> user = service.getDeptByName(department);
		responseDTO response = new responseDTO(user,"User by Department");
		return new ResponseEntity<responseDTO>(response,HttpStatus.OK);
	}
	
	
}
