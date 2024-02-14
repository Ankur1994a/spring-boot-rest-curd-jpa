package com.ankur.srpingboot.curddemo.service;

import com.ankur.srpingboot.curddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int employeeID);

    Employee save(Employee employee);

    void deleteById(int id);

    List<Employee> findByName(String name);


}
