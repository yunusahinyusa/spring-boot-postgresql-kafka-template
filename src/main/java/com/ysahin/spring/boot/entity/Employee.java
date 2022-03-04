package com.ysahin.spring.boot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "employeeName",length = 200)
	private String employeeName;

	@Column(name = "EmployeeEmail", length = 200)
	private String EmployeeEmail;

}
