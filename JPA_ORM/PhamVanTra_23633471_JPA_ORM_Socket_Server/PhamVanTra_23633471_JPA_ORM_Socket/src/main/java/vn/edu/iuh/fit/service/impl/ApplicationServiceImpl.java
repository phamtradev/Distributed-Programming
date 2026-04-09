package vn.edu.iuh.fit.service.impl;

import vn.edu.iuh.fit.dto.ApplicationDTO;
import vn.edu.iuh.fit.mapper.JacksonDataMapper;
import vn.edu.iuh.fit.model.Application;
import vn.edu.iuh.fit.model.ApplicationId;
import vn.edu.iuh.fit.model.Candidate;
import vn.edu.iuh.fit.model.Job;
import vn.edu.iuh.fit.repository.impl.ApplicationRepositoryImpl;
import vn.edu.iuh.fit.service.ApplicationService;

import java.util.List;
import java.util.stream.Collectors;

public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepositoryImpl applicationRepository = new ApplicationRepositoryImpl(Application.class);
    private final JacksonDataMapper mapper = new JacksonDataMapper();

    @Override
    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.loadAll().stream()
                .map(this::toApplicationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO getApplicationById(String candidateId, String jobId) {
        ApplicationId id = new ApplicationId(candidateId, jobId);
        return toApplicationDTO(applicationRepository.findById(id));
    }

    @Override
    public ApplicationDTO saveApplication(ApplicationDTO applicationDTO) {
        Application application = toApplicationEntity(applicationDTO);
        Application saved = applicationRepository.create(application);
        return toApplicationDTO(saved);
    }

    @Override
    public void deleteApplication(String candidateId, String jobId) {
        ApplicationId id = new ApplicationId(candidateId, jobId);
        applicationRepository.delete(id);
    }

    // Application có cấu trúc phẳng trong DTO (candidateName, jobTitle)
    // nhưng lồng nhau trong entity => xử lý thủ công
    private ApplicationDTO toApplicationDTO(Application app) {
        if (app == null) return null;
        ApplicationDTO dto = new ApplicationDTO();
        dto.setAppliedDate(app.getAppliedDate());
        dto.setStatus(app.getStatus());
        if (app.getCandidate() != null) {
            dto.setCandidateId(app.getCandidate().getId());
            dto.setCandidateName(app.getCandidate().getName());
        }
        if (app.getJob() != null) {
            dto.setJobId(app.getJob().getId());
            dto.setJobTitle(app.getJob().getTitle());
        }
        return dto;
    }

    private Application toApplicationEntity(ApplicationDTO dto) {
        if (dto == null) return null;
        ApplicationId id = new ApplicationId(dto.getCandidateId(), dto.getJobId());
        Candidate candidate = Candidate.builder().id(dto.getCandidateId()).build();
        Job job = Job.builder().id(dto.getJobId()).build();
        return Application.builder()
                .id(id)
                .appliedDate(dto.getAppliedDate())
                .status(dto.getStatus())
                .candidate(candidate)
                .job(job)
                .build();
    }
}
