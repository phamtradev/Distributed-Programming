package vn.edu.iuh.fit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    private String name;
    private int age;
    private double gpa;

    private Address address;
}
