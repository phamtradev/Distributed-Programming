package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.model.Doctor;

import java.util.Map;

public interface DoctorService {

    Doctor findDoctorById(String doctorId);

    Map<String, Long> getNoOfDoctorBySpeciality(String departmentName);

    boolean addDoctor(Doctor doctor);

}
