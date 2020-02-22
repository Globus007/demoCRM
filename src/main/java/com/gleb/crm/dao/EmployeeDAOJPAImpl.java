package com.gleb.crm.dao;

import com.gleb.crm.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@Primary
public class EmployeeDAOJPAImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        Query query = entityManager.createQuery("from Employee");
        return query.getResultList();
    }

    @Override
    public void deleteById(int id) {
        Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }

    @Override
    public void save(Employee employee) {
        Employee tempEmployee = entityManager.merge(employee);
        // for returning Employee in REST app
        employee.setId(tempEmployee.getId());
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }
}
