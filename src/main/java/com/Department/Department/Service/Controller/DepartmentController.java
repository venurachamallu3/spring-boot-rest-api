package com.Department.Department.Service.Controller;

import com.Department.Department.Service.Entity.Department;
import com.Department.Department.Service.Exceptions.DepartmentNotFoundException;
import com.Department.Department.Service.Service.DepartmentServiceImpl;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
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


    @GetMapping("v1/fetch/{id}")
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



    @GetMapping("v2/fetch/{id}")
    public Department fetchDepartmentByIDusingV2(@PathVariable("id") Long departmentId) throws  DepartmentNotFoundException{
        return departmentServiceImpl.getDepartmentByIdusingV2(departmentId);
    }

    @GetMapping(value = "/fetch/{id}",params = "version=3")
    public Department fetchDepartmentByIdusingQueryParam(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException{
        return departmentServiceImpl.getDepartmentByIdusingQueryParam(departmentId);
    }

    @GetMapping(value = "/fetch/{id}/header",headers = "version=4")
    public Department fetchDepartmentByIdusingHeaders(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException{
        return departmentServiceImpl.getDepartmentByIdusingHeaders(departmentId);
    }


    @GetMapping("/fetch/{id}/withlink")
    public EntityModel<Department> getDepartmentByIdAddedLinksAlso(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException
    {
        Department department = departmentServiceImpl.getDepartmentByIdusingHeaders(departmentId);
        EntityModel<Department> entityModel= EntityModel.of(department);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).fetchDepartments());
        entityModel.add(link.withRel("all-departments"));
        return entityModel;
    }



    //filter for list of  departments
//    @GetMapping("/fetch/filtervalues")
    public MappingJacksonValue fetchDepartmentsWithFilters(){
        log.info("fetchDepartments in the DepartmentController");
        List<Department> department= departmentServiceImpl.fetchDepartments();
        MappingJacksonValue  mappingJacksonValue = new MappingJacksonValue(department);
        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("id","departmentName","departmentAddress");
        FilterProvider filters = new SimpleFilterProvider().addFilter("departmentFilter",filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }




    //filter for particular of  department
//    @GetMapping("/fetch/{id}/filtervalues")
    public MappingJacksonValue fetchDepartmentsWithFilters(@PathVariable("id") Long departmnetId) throws  DepartmentNotFoundException{
        log.info("fetchDepartments in the DepartmentController");
        Department department= departmentServiceImpl.getDepartmentById(departmnetId);
        MappingJacksonValue  mappingJacksonValue = new MappingJacksonValue(department);
        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("id","departmentName","departmentAddress");
        FilterProvider filters = new SimpleFilterProvider().addFilter("departmentFilter",filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

}
