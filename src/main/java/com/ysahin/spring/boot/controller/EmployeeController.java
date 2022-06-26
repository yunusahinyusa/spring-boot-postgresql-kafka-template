package com.ysahin.spring.boot.controller;

import com.ysahin.spring.boot.dto.EmployeeDto;
import com.ysahin.spring.boot.entity.ApiS;
import com.ysahin.spring.boot.service.ApiService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ysahin.spring.boot.service.EmployeeService;
import com.ysahin.spring.boot.util.UtilService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    private final EmployeeService employeeService;
    private final UtilService utilService;
    private final ApiService apiService;

    public EmployeeController(KafkaTemplate<String, String> kafkaTemplate, EmployeeService employeeService, UtilService utilService, ApiService apiService) {
        this.kafkaTemplate = kafkaTemplate;
        this.employeeService = employeeService;
        this.utilService = utilService;
        this.apiService = apiService;
    }

    @GetMapping("/apis")
    public String getApiS(Model model){
        LocalDateTime instance = LocalDateTime.now();
        LocalDateTime instance2 = instance.minusHours(1);
        List<ApiS> apis = apiService.apiCreatedDate(instance2);
        model.addAttribute("apis",apis);
        return "apis";
    }

    @GetMapping("/apis2")
    public ModelAndView getApiS2(Model model){
        LocalDateTime instance = LocalDateTime.now();
        LocalDateTime instance2 = instance.minusHours(1);
        List<ApiS> apiSList = apiService.apiCreatedDate(instance2);
        System.out.println(apiSList);
        ModelAndView mav = new ModelAndView();
        mav.addObject(apiSList);
        mav.setViewName("apis");
        System.out.println(mav);
        return mav;
    }
    @GetMapping("/charts2")
    public ModelAndView getCharts(Model model){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
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


