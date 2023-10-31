package widetest.simpleapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import widetest.simpleapp.model.Department;
import widetest.simpleapp.model.Employee;
import widetest.simpleapp.repository.DepartmentRepository;
import widetest.simpleapp.repository.EmployeeRepository;
import widetest.simpleapp.request.EmployeeRequest;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Employee createEmployee(EmployeeRequest employeeRequest){
        Employee employee = new Employee();
        employee.setEmployeeName(employeeRequest.getName());
        employee.setEmployeeEmail(employeeRequest.getEmail());

        Optional<Department> department = departmentRepository.findById(employeeRequest.getDepartmentId());
        if(department.isPresent()){
            employee.setDepartment(department.get());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> retrieveListEmployees(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee getSingleEmployeeById (Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            return employee.get();
        }
        throw new RuntimeException("Employee is not found for the id: " + id);
    }

    @Override
    public Employee updateEmployee(Long idEmployee, EmployeeRequest employeeUpdate){
        Employee employee = getSingleEmployeeById(idEmployee);

        employee.setEmployeeName(employeeUpdate.getName());
        employee.setEmployeeEmail(employeeUpdate.getEmail());
        Optional<Department> department = departmentRepository.findById(employeeUpdate.getDepartmentId());
        if(department.isPresent()){
            employee.setDepartment(department.get());
        }

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }


}
