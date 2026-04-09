package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.dto.CandidateDTO;

import java.util.List;

public interface CandidateService {

    List<CandidateDTO> getCandidatesBySkill(String skill);

    CandidateDTO getCandidateById(String id);

    CandidateDTO saveCandidate(CandidateDTO candidateDTO);

    void deleteCandidate(String id);
}