package widetest.simpleapp.service;
import widetest.simpleapp.model.Employee;
import widetest.simpleapp.request.EmployeeRequest;

import java.util.List;
public interface EmployeeService {
    Employee createEmployee (EmployeeRequest employeeRequest);

    List<Employee> retrieveListEmployees();

    Employee getSingleEmployeeById (Long id);

    Employee updateEmployee(Long idEmployee, EmployeeRequest employeeUpdate);

    void deleteEmployee(Long id);
}
