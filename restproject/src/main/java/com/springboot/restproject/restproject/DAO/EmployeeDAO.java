package com.springboot.restproject.restproject.DAO;

import com.springboot.restproject.restproject.entity.Employee;

import java.util.List;

public interface EmployeeDAO
{
    List<Employee> findAll();

    public Employee findById(int theID);

    public void save(Employee theemployee);

    public void deleteById(int theID);

}
