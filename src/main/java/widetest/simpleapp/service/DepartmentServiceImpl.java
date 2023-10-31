package widetest.simpleapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import widetest.simpleapp.model.Department;
import widetest.simpleapp.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }
    @Override
    public List<Department> retrieveListDepartment(){
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id){
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isPresent()){
            return department.get();
        }

        throw new RuntimeException("Department is not found for the id: " + id);
    }

    @Override
    public Department updateDepartment(Long idDepartment, Department departmentUpdate){
        Department department = getDepartmentById(idDepartment);

        department.setDepartmentName(departmentUpdate.getDepartmentName());
        department.setDepartmentAddress(departmentUpdate.getDepartmentAddress());
        department.setDepartmentCode(departmentUpdate.getDepartmentCode());

        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id){
        departmentRepository.deleteById(id);
    }
}
