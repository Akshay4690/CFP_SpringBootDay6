package com.demo.employee.entity;

import java.util.Date;
import java.util.List;

import com.demo.employee.dto.EmployeeModelDTO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class EmployeeModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;
	private String name;
	private String gender;
	private Date startDate;
	private long salary;
	private String profilePic;
	private String note;
	
	@ElementCollection
	@CollectionTable(name = "employee_department" , joinColumns = @JoinColumn(name="dID"))
	private List<String> department;
		
	public EmployeeModel(EmployeeModelDTO model) {
		
		this.name = model.getName();
		this.gender = model.getGender();
		this.startDate = model.getStartDate();
		this.salary = model.getSalary();
		this.profilePic = model.getProfilePic();
		this.note = model.getNote();
		this.department = model.getDepartment();
	}


}
