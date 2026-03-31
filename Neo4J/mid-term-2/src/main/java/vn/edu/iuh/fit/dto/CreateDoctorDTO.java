package vn.edu.iuh.fit.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CreateDoctorDTO {

    private String doctorId;
    private String departmentId;
    private String phone;
    private String name;
    private String speciality;
}
