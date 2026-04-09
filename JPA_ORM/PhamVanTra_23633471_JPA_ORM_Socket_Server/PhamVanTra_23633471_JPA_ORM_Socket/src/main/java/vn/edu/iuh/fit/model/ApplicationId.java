package vn.edu.iuh.fit.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ApplicationId implements Serializable {

    @Column(name = "candidate_id")
    private String candidateId;

    @Column(name = "job_id")
    private String jobId;
}
