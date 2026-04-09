package vn.edu.iuh.fit.repository.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.constant.JobStatus;
import vn.edu.iuh.fit.model.Candidate;
import vn.edu.iuh.fit.repository.CandidateRepository;
import vn.edu.iuh.fit.util.JPAUtils;

import java.util.List;

public class CandidateRepositoryImpl extends GenericRepositoryImpl<Candidate, String> implements CandidateRepository {

    public CandidateRepositoryImpl() {
        super(Candidate.class);
    }

    @Override
    public List<Candidate> findCandidateBySkillInOpenJobs(String skill) {
        EntityManager em = JPAUtils.getEntityManager();
        try {
            String jpql = """
                    SELECT DISTINCT c
                    FROM Candidate c
                    JOIN c.skills s
                    JOIN c.applications a
                    JOIN a.job j
                    WHERE LOWER(s.name) = LOWER(:skill)
                      AND j.status = :status
                    """;

            return em.createQuery(jpql, Candidate.class)
                    .setParameter("skill", skill)
                    .setParameter("status", JobStatus.OPEN)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
