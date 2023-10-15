package com.Department.Department.Service.Entity;


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
public class Department {


@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
@NotBlank(message = "Department Name is required pleasee add......")
@Length(max = 10 , min = 3, message = "Department name length should be in the range from 3 to 10")
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;

}
