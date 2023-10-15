package com.Department.Department.Service.Controller;

import com.Department.Department.Service.Entity.Department;
import com.Department.Department.Service.Exceptions.DepartmentNotFoundException;
import com.Department.Department.Service.Service.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    public DepartmentServiceImpl departmentServiceImpl;

    private final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("add")
    public Department saveDepartment( @Valid @RequestBody Department department){
        log.info("saveDepartment in the DepartmentController");
        return departmentServiceImpl.saveDepartment(department);
    }

    @GetMapping("/fetch")
    public List<Department> fetchDepartments(){
        log.info("fetchDepartments in the DepartmentController");
        return departmentServiceImpl.fetchDepartments();
    }


    @GetMapping("/fetch/{id}")
    public Department getDepartmentById(@PathVariable("id") Long departmentId)
            throws DepartmentNotFoundException {
        log.info("getDepartmentById in the DepartmentController");
        return departmentServiceImpl.getDepartmentById(departmentId);
    }

    @DeleteMapping("/delete/{Id}")
    public String  deleteDepartmentById(@PathVariable Long Id) throws DepartmentNotFoundException{
        log.info("deleteDepartmentById in the DepartmentController");
        departmentServiceImpl.deleteDepartmentById(Id);
        return "Successfully Deleted.....";
    }


    @GetMapping("fetch/department/{name}")
    public Department getDepartmentByName(@PathVariable("name") String departmentName){
        log.info("getDepartmentByName in the DepartmentController");
        return departmentServiceImpl.getDepartmentByName(departmentName);
    }


    @PutMapping("update/{id}")
    public  Department updateDepartment(@PathVariable("id") Long departmentId , @RequestBody Department department){

        log.info("updateDepartment in the DepartmentController");

        return departmentServiceImpl.updateDepartment(departmentId,department);
    }

    @GetMapping("/count")
    public Integer countDepartments(){
        log.info(" countDepartments in the DepartmentController");
        return departmentServiceImpl.countDepartments();
    }

}
