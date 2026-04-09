package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.dto.ApplicationDTO;

import java.util.List;

public interface ApplicationService {

    List<ApplicationDTO> getAllApplications();

    ApplicationDTO getApplicationById(String candidateId, String jobId);

    ApplicationDTO saveApplication(ApplicationDTO applicationDTO);

    void deleteApplication(String candidateId, String jobId);
}
