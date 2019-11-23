package com.springboot.restproject.restproject.restcontroller;

import com.springboot.restproject.restproject.DAO.EmployeeDAO;
import com.springboot.restproject.restproject.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController
{
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeController(EmployeeDAO employeeDAO)
    {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("employee")
    public List<Employee> findAll()
    {
        return employeeDAO.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId)
    {
     Employee employee=employeeDAO.findById(employeeId);

     if(employee == null)
     {
         throw new RuntimeException("Employee id not found");
     }

     return employee;
    }

    @PostMapping("/addemployee")
    public Employee addEmployee(@RequestBody Employee theemployee)
    {
        theemployee.setId(0);
        employeeDAO.save(theemployee);
        return theemployee;
    }

    @PutMapping("/putemployee")
    public Employee putemployee(@RequestBody Employee theemployee)
    {
        employeeDAO.save(theemployee);

        return theemployee;
    }

    @DeleteMapping("/deleteemployee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId)
    {
        Employee employee=employeeDAO.findById(employeeId);

        if(employee == null)
        {
            throw new RuntimeException("Employee id not found");
        }
        employeeDAO.deleteById(employeeId);

        return "Deleted Employee"+employeeId;

    }

}
