package vn.edu.iuh.fit.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = "appointments")
@Entity
@Table(name = "Doctors")
@PrimaryKeyJoinColumn(name = "doctorId")
public class Doctor extends Person {

    @Column(name = "specialty", length = 255)
    private String specialty;

    @Column(name = "hospital", length = 255)
    private String hospital;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();
}