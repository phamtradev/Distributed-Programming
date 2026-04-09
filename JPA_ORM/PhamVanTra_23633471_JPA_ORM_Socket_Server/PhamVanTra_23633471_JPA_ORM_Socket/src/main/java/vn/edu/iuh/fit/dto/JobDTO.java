package vn.edu.iuh.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.constant.JobStatus;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO implements Serializable {

    private String id;
    private String title;
    private String description;
    private double salary;
    private JobStatus status;
    private String companyId;
    private String companyName;
    private Set<SkillDTO> skills;
}