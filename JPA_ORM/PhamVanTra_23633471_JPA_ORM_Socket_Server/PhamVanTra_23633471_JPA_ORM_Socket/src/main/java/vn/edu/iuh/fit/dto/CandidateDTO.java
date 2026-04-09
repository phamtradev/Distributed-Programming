package vn.edu.iuh.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO implements Serializable {

    private String id;
    private String name;
    private String email;
    private int experience;
    private Set<SkillDTO> skills;
}
