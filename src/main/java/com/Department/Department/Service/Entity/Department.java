package com.Department.Department.Service.Entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"departmentName","departmentCode"})
//@JsonFilter("departmentFilter")
public class Department {


@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
@NotBlank(message = "Department Name is required pleasee add......")
@Length(max = 10 , min = 3, message = "Department name length should be in the range from 3 to 10")
    private String departmentName;
//    @JsonProperty("Department Address")
//    @JsonIgnore
    private String departmentAddress;
    private String departmentCode;

}
