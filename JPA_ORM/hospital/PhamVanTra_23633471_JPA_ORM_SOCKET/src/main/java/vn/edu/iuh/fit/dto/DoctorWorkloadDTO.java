package vn.edu.iuh.fit.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorWorkloadDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String doctorId;
    private String doctorName;
    private LocalDate date;
    private long totalAppointments;
}
