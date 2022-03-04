package com.ysahin.spring.boot.controller;

import com.ysahin.spring.boot.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import com.ysahin.spring.boot.service.EmployeeService;
import com.ysahin.spring.boot.util.UtilService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private final EmployeeService employeeService;
    private final UtilService utilService;

    public EmployeeController(KafkaTemplate<String, String> kafkaTemplate, EmployeeService employeeService, UtilService utilService) {
        this.kafkaTemplate = kafkaTemplate;
        this.employeeService = employeeService;
        this.utilService = utilService;
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employee, HttpServletRequest request) throws Exception {
        EmployeeDto resultEmployee = employeeService.createEmployee(employee,request);
        utilService.readLastLine();
        return ResponseEntity.ok(resultEmployee);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDto>> getEmployees(HttpServletRequest request) throws IOException {
        List<EmployeeDto> employees =  employeeService.getEmployees(request);
        utilService.readLastLine();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long id,HttpServletRequest request) throws Exception {
        EmployeeDto employeeDto = employeeService.getEmployeeById(id,request);
        utilService.readLastLine();
        return ResponseEntity.ok(employeeDto);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id,@RequestBody EmployeeDto employee,HttpServletRequest request)
            throws Exception {
        EmployeeDto resultEmployee =  employeeService.updateEmployee(id,employee,request);
        utilService.readLastLine();
        return ResponseEntity.ok(resultEmployee);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") Long id,HttpServletRequest request) throws IOException {
        Boolean status = employeeService.deleteEmployee(id,request);
        utilService.readLastLine();
        return ResponseEntity.ok(status);
    }
}


