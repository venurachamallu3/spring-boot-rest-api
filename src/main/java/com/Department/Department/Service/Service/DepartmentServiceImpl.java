package com.Department.Department.Service.Service;

import com.Department.Department.Service.Entity.Department;
import com.Department.Department.Service.Exceptions.DepartmentNotFoundException;
import com.Department.Department.Service.Repository.DepartmentRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl {

    private final Logger log = (Logger) LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    public DepartmentRepository departmentRepository;



    public List<Department> fetchDepartments() {

        log.info("fetchDepartments in the Department Service....");
        return departmentRepository.findAll();
    }


    public Department saveDepartment(Department department) {
        log.info("saveDepartments in the Department Service....");
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        log.info("getDepartmentById in the Department Service....");
      Optional<Department> department = departmentRepository.findById(departmentId);
//        if(department.isPresent()){
//
//
//        }
        if (department.isPresent()){
            return department.get();
        }
        throw new DepartmentNotFoundException(" Department Not Available ....");

//        return departmentRepository.findById(departmentId).get();


    }

    public void deleteDepartmentById(Long id) throws  DepartmentNotFoundException{
        log.info("deleteDepartmentByID in the Department Service....");
        Optional<Department> department = departmentRepository.findById(id);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Available ....");
        }
        departmentRepository.deleteById(id);
    }

    public Department getDepartmentByName(String departmentName) {
        log.info("getDepartmentByName in the Department Service....");
       return departmentRepository.findByDepartmentName(departmentName);
    }

    public Department updateDepartment(Long departmentId, Department department) {
        log.info("updateDepartment in the Department Service....");
        Department dep = departmentRepository.findById(departmentId).get();
//        int depid = Math.toIntExact(dep.getId());

            dep.setDepartmentAddress(department.getDepartmentAddress());
            dep.setDepartmentName(department.getDepartmentName());
            dep.setDepartmentCode(department.getDepartmentCode());
        return departmentRepository.save(dep);
    }

    public Integer countDepartments() {
        log.info("countDepartments in the Department Service....");
        return Math.toIntExact(departmentRepository.count());
    }

    public Department getDepartmentByIdusingV2(Long departmentId) throws  DepartmentNotFoundException{
        Optional<Department> department = departmentRepository.findById(departmentId);
//        if(department.isPresent()){
//
//
//        }
        if (department.isPresent()){
            department.get().setDepartmentName(department.get().getDepartmentName()+"-"+department.get().getDepartmentCode());
            return department.get();
        }
        throw new DepartmentNotFoundException(" Department Not Available ....");


    }

    public Department getDepartmentByIdusingQueryParam(Long departmentId) throws  DepartmentNotFoundException{
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()){
            department.get().setDepartmentName(department.get().getDepartmentName()+"-"+department.get().getDepartmentCode()+"-"+(department.get().getDepartmentAddress()).charAt(0));
            return department.get();
        }
        throw new DepartmentNotFoundException(" Department Not Available ....");
    }

    public Department getDepartmentByIdusingHeaders(Long departmentId) throws DepartmentNotFoundException {

        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()){
            department.get().setDepartmentAddress((department.get().getDepartmentAddress().toUpperCase()));
        return department.get();
        }
        throw new DepartmentNotFoundException(" Department Not Available ....");
    }
}
