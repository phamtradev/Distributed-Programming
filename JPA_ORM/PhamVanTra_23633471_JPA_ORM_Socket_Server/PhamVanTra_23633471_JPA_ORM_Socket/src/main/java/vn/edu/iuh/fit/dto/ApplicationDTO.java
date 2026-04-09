package vn.edu.iuh.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.constant.AppStatus;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO implements Serializable {

    private String candidateId;
    private String candidateName;
    private String jobId;
    private String jobTitle;
    private LocalDate appliedDate;
    private AppStatus status;
}