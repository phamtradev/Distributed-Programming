package vn.edu.iuh.fit.repository;

import vn.edu.iuh.fit.model.Job;

import java.util.List;

public interface JobRepository extends GenericRepository<Job, String> {

    List<Job> countPerJobByCompany(String companyName);

}
