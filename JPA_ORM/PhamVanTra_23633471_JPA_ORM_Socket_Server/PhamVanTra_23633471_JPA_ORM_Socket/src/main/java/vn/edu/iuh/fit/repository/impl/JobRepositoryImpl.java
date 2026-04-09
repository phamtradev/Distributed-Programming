package vn.edu.iuh.fit.repository.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.model.Job;
import vn.edu.iuh.fit.repository.JobRepository;
import vn.edu.iuh.fit.util.JPAUtils;

import java.util.List;

public class JobRepositoryImpl extends GenericRepositoryImpl<Job, String> implements JobRepository {

    public JobRepositoryImpl() {
        super(Job.class);
    }

    @Override
    public List<Job> countPerJobByCompany(String companyName) {
        EntityManager em = JPAUtils.getEntityManager();
        try {
            String jpql = """
                    SELECT j
                    FROM Job j
                    LEFT JOIN j.applications a
                    JOIN j.company c
                    WHERE LOWER(c.name) = LOWER(:companyName)
                    GROUP BY j
                    ORDER BY COUNT(a) DESC
                    """;

            return em.createQuery(jpql, Job.class)
                    .setParameter("companyName", companyName)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
