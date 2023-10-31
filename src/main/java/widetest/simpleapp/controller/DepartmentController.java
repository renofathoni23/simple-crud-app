package widetest.simpleapp.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import widetest.simpleapp.model.Department;
import widetest.simpleapp.service.DepartmentService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/department")
    private ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else{
            Department department1 =  departmentService.createDepartment(department);
            return new ResponseEntity<Department>(department1, HttpStatus.CREATED);
        }
    }

    @GetMapping("/departments")
    private ResponseEntity<List<Department>> getDepartments(){
        return new ResponseEntity<List<Department>>(departmentService.retrieveListDepartment(), HttpStatus.OK);
    };

    @GetMapping("/department/{id}")
    private ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id){
        try{
            Department department = departmentService.getDepartmentById(id);
            return new ResponseEntity<Department>(department, HttpStatus.OK);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Department " + String.valueOf(id) + " Not Found");
        }
    }

    @PutMapping("/department/{id}")
    private ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id, @RequestBody Department departmentUpdate){
        try{
            Department department = departmentService.updateDepartment(id, departmentUpdate);
            return new ResponseEntity<Department>(department, HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Department " + String.valueOf(id) + " Not Found");
        }
    }

    @DeleteMapping("/department")
    public ResponseEntity<String> deleteDepartment(@RequestParam("id") Long id){
        departmentService.deleteDepartment(id);
        return new ResponseEntity<String>("Department has been deleted", HttpStatus.OK);
    }
}
