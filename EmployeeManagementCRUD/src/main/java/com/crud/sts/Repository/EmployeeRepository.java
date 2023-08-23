package com.crud.sts.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crud.sts.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
