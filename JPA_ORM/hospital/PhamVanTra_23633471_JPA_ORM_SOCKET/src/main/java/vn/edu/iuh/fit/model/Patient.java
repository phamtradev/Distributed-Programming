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
@Table(name = "Patients")
@PrimaryKeyJoinColumn(name = "patientId")
public class Patient extends Person {

    @ElementCollection
    @CollectionTable(
            name = "Phones",
            joinColumns = @JoinColumn(name = "patientId")
    )
    @Column(name = "phoneNumber", length = 255)
    private Set<String> phones = new HashSet<>();

    @Column(name = "address", length = 255)
    private String address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments = new HashSet<>();
}