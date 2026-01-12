package vn.edu.iuh.fit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    private String name;
    private int age;
    private double gpa;
    private Address address;
}
