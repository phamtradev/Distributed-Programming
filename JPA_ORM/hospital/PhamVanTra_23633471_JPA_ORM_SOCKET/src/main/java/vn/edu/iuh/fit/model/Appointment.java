package vn.edu.iuh.fit.model;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.constant.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"doctor", "patient"})
@Entity
@Table(name = "Appointments")
public class Appointment {

    @EmbeddedId
    private AppointmentId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("doctorId")
    @JoinColumn(name = "doctorId", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("patientId")
    @JoinColumn(name = "patientId", nullable = false)
    private Patient patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 255)
    private Status status;
}