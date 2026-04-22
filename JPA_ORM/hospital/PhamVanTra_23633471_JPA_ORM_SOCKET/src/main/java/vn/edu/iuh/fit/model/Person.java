package vn.edu.iuh.fit.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "People")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @Column(name = "personId", nullable = false, length = 255)
    protected String id;

    @Column(name = "fullName", length = 255)
    protected String fullName;

    @Column(name = "email", length = 255)
    protected String email;
}