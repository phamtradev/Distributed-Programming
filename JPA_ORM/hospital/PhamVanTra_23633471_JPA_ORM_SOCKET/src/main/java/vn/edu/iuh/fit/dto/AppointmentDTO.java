package vn.edu.iuh.fit.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppointmentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String doctorId;
    private String doctorName;
    private String patientId;
    private String patientName;
    private LocalDateTime appointmentTime;
    private String status;
}
