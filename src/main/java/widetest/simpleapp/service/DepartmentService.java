package widetest.simpleapp.service;

import widetest.simpleapp.model.Department;
import widetest.simpleapp.model.Employee;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);
    List<Department> retrieveListDepartment();

    Department getDepartmentById(Long id);

    Department updateDepartment(Long idDepartment, Department departmentUpdate);

    void deleteDepartment(Long id);

}
