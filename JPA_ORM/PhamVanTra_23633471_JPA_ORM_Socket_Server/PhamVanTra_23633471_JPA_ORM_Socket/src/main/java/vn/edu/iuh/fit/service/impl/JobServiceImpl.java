package vn.edu.iuh.fit.service.impl;

import vn.edu.iuh.fit.dto.JobDTO;
import vn.edu.iuh.fit.mapper.JacksonDataMapper;
import vn.edu.iuh.fit.model.Job;
import vn.edu.iuh.fit.repository.impl.JobRepositoryImpl;
import vn.edu.iuh.fit.service.JobService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JobServiceImpl extends UnicastRemoteObject implements JobService {

    private final JobRepositoryImpl jobRepository = new JobRepositoryImpl();
    private final JacksonDataMapper mapper = new JacksonDataMapper();

    public JobServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public List<JobDTO> getAllJobs() throws RemoteException {
        return jobRepository.loadAll().stream()
                .map(this::toJobDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobDTO getJobById(String id) throws RemoteException {
        return toJobDTO(jobRepository.findById(id));
    }

    @Override
    public JobDTO saveJob(JobDTO jobDTO) throws RemoteException {
        Job job = toJobEntity(jobDTO);
        Job saved = jobRepository.create(job);
        return toJobDTO(saved);
    }

    @Override
    public void deleteJob(String id) throws RemoteException {
        jobRepository.delete(id);
    }

    @Override
    public List<JobDTO> getJobsByCompany(String companyName) throws RemoteException {
        return jobRepository.countPerJobByCompany(companyName).stream()
                .map(this::toJobDTO)
                .collect(Collectors.toList());
    }

    private JobDTO toJobDTO(Job job) {
        if (job == null) return null;
        Map<String, Object> map = mapper.toMap(job);
        if (job.getCompany() != null) {
            map.put("companyId", job.getCompany().getId());
            map.put("companyName", job.getCompany().getName());
        }
        map.remove("company");
        return mapper.toObject(map, JobDTO.class);
    }

    private Job toJobEntity(JobDTO jobDTO) {
        if (jobDTO == null) return null;
        Map<String, Object> map = mapper.toMap(jobDTO);
        if (jobDTO.getCompanyId() != null) {
            map.put("company", Map.of("id", jobDTO.getCompanyId(), "name",
                    jobDTO.getCompanyName() != null ? jobDTO.getCompanyName() : ""));
        }
        map.remove("companyId");
        map.remove("companyName");
        return mapper.toObject(map, Job.class);
    }
}
