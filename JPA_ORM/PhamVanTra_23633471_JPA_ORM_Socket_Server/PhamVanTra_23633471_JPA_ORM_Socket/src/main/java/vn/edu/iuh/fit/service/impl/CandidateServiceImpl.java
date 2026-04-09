package vn.edu.iuh.fit.service.impl;

import vn.edu.iuh.fit.dto.CandidateDTO;
import vn.edu.iuh.fit.mapper.JacksonDataMapper;
import vn.edu.iuh.fit.model.Candidate;
import vn.edu.iuh.fit.repository.impl.CandidateRepositoryImpl;
import vn.edu.iuh.fit.service.CandidateService;

import java.util.List;
import java.util.stream.Collectors;

public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepositoryImpl candidateRepository = new CandidateRepositoryImpl();
    private final JacksonDataMapper mapper = new JacksonDataMapper();

    @Override
    public List<CandidateDTO> getCandidatesBySkill(String skill) {
        return candidateRepository.findCandidateBySkillInOpenJobs(skill).stream()
                .map(candidate -> mapper.toObject(mapper.toMap(candidate), CandidateDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CandidateDTO getCandidateById(String id) {
        Candidate candidate = candidateRepository.findById(id);
        return mapper.toObject(mapper.toMap(candidate), CandidateDTO.class);
    }

    @Override
    public CandidateDTO saveCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = mapper.toObject(mapper.toMap(candidateDTO), Candidate.class);
        Candidate saved = candidateRepository.create(candidate);
        return mapper.toObject(mapper.toMap(saved), CandidateDTO.class);
    }

    @Override
    public void deleteCandidate(String id) {
        candidateRepository.delete(id);
    }
}
