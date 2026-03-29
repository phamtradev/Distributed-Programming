package vn.edu.iuh.fit.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Doctor {

    private String doctorId;
    private String departmentId;
    private String phone;
    private String name;
    private String speciality;
}
