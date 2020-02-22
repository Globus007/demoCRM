package com.gleb.crm.dao;

import com.gleb.crm.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    void deleteById(int id);

    void save(Employee employee);

    Employee findById(int id);
}
