package com.ankur.srpingboot.curddemo.rest;

import com.ankur.srpingboot.curddemo.entity.Employee;
import com.ankur.srpingboot.curddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    //private EmployeeDAO employeeDAO;

    private EmployeeService employeeService;

    /*@Autowired
    public EmployeeRestController(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }*/

    @Autowired
    public EmployeeRestController(EmployeeService service){
        this.employeeService = service;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        //return employeeDAO.findAll();

        return employeeService.findAll();
    }


    // Get the employee by Id {Employee Id}

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);

        if(employee == null){
            throw new RuntimeException("Employee not found with Id -- "+employeeId);
        }
        return employee;
    }

    // Add mapping for adding Employee by Post Method
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        // set id to 0 if they pass any id

        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // Add mapping for update employee using PUT method
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee dbEmp = employeeService.save(employee);

        return dbEmp;
    }


    // Add mapping for deleting Employee using DELETE method
    @DeleteMapping("/employees/{employeeId}")
    public String deleteById(@PathVariable int employeeId){

        Employee dbEmp = employeeService.findById(employeeId);

        if(dbEmp == null){
            throw new RuntimeException("Employee not found with Id -- "+employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee from Database of id -- "+employeeId;
    }

    @GetMapping("/employees/name/{employeeName}")
    public List<Employee> findByName(@PathVariable String employeeName){
        return employeeService.findByName(employeeName);
    }
}
