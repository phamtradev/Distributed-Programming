package vn.edu.iuh.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateJobApplicationDTO implements Serializable {

    private String candidateId;
    private String candidateName;
    private String jobTitle;
    private LocalDate appliedDate;

}
