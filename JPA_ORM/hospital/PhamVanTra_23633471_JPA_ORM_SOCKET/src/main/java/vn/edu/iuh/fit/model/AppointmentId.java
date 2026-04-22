package vn.edu.iuh.fit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class AppointmentId implements Serializable {

    @Column(name = "doctorId", length = 255)
    private String doctorId;

    @Column(name = "patientId", length = 255)
    private String patientId;

    @Column(name = "appointmentTime", nullable = false)
    private LocalDateTime appointmentTime;
}