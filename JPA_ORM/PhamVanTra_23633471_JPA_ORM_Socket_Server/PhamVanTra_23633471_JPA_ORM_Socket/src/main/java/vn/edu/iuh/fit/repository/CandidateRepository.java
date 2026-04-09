package vn.edu.iuh.fit.repository;

import vn.edu.iuh.fit.model.Candidate;

import java.util.List;

public interface CandidateRepository extends GenericRepository<Candidate, String>{

    List<Candidate> findCandidateBySkillInOpenJobs(String skill);

}
