package widetest.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import widetest.simpleapp.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
