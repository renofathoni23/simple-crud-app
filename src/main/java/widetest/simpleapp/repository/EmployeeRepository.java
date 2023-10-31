package widetest.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import widetest.simpleapp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
