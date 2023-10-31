package widetest.simpleapp.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import widetest.simpleapp.model.Employee;
import widetest.simpleapp.request.EmployeeRequest;
import widetest.simpleapp.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    private ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRequest employee, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else{
            Employee employee1 = employeeService.createEmployee(employee);
            return new ResponseEntity<Employee>(employee1, HttpStatus.CREATED);
        }
    }

    @GetMapping("/employees")
    private ResponseEntity<List<Employee>> getEmployees(){
      return new ResponseEntity<List<Employee>>(employeeService.retrieveListEmployees(), HttpStatus.OK);
    };

    @GetMapping("/employee/{id}")
    private ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        try{
            Employee employee = employeeService.getSingleEmployeeById(id);
            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Employee " + String.valueOf(id) + " Not Found");
        }
    }

    @PutMapping("/employee/{id}")
    private ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeRequest employee){
        try{
            Employee employee1 = employeeService.updateEmployee(id, employee);
            return new ResponseEntity<Employee>(employee1, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Employee " + String.valueOf(id) + " Not Found");
        }
    }

    @DeleteMapping("/employee")
    public ResponseEntity<String> deleteEmployee(@RequestParam("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee has been deleted", HttpStatus.OK);
    }




}
