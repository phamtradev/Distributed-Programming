package vn.edu.iuh.fit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @Column(name = "skill_id", length = 50)
    private String id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "skills")
    private Set<Candidate> candidates = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    private Set<Job> jobs = new HashSet<>();

}
