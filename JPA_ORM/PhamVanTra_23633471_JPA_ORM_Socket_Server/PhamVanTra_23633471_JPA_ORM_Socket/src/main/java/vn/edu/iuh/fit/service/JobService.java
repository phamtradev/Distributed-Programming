package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.dto.JobDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface JobService extends Remote {

    List<JobDTO> getAllJobs() throws RemoteException;

    JobDTO getJobById(String id) throws RemoteException;

    JobDTO saveJob(JobDTO jobDTO) throws RemoteException;

    void deleteJob(String id) throws RemoteException;

    List<JobDTO> getJobsByCompany(String companyName) throws RemoteException;
}
