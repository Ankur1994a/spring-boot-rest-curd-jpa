package com.ankur.srpingboot.curddemo.service;

import com.ankur.srpingboot.curddemo.dao.EmployeeRepository;
import com.ankur.srpingboot.curddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository){
        employeeRepository = repository;
    }

    @Override
    @Cacheable(value = "employees")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Cacheable(value = "employee",key = "#employeeID")
    public Employee findById(int employeeID) {
        Optional<Employee> result = employeeRepository.findById(employeeID);

        if(result.isPresent()){
            return result.get();
        }
        else {
            throw new RuntimeException("Employee not found with id -- " + employeeID);
        }
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByName(String name) {
        List<Employee> result = employeeRepository.findByFirstName(name);

        if(result.size()>0){
            return result;
        }
        else {
            throw new RuntimeException("Employee not found with name -- " + name);
        }
    }
}
