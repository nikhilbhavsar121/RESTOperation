package com.springboot.restproject.restproject.DAO;

import com.springboot.restproject.restproject.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO
{

    EntityManager entityManager;

    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll()
    {
        Session currentsession=entityManager.unwrap(Session.class);

        Query<Employee> employeeQuery=currentsession.createQuery("From Employee", Employee.class);

        List<Employee> employees= employeeQuery.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int theID)
    {
        Session currentsession=entityManager.unwrap(Session.class);

        Employee theemployee=currentsession.get(Employee.class,theID);


        return theemployee;
    }

    @Override
    public void save(Employee theemployee)
    {

        Session currentsession=entityManager.unwrap(Session.class);

        currentsession.saveOrUpdate(theemployee);

    }

    @Override
    @Transactional
    public void deleteById(int theID)
    {
        Session currentsession=entityManager.unwrap(Session.class);
        Query thequery =currentsession.createQuery("delete from Employee where id=:employeeId");
        thequery.setParameter("employeeId",theID);
        thequery.executeUpdate();
}
}
