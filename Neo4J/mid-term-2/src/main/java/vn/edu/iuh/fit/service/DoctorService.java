package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.model.Doctor;

import java.util.Map;

public interface DoctorService {

    Doctor findDoctorById(String doctorId);

    Map<String, Long> getNoOfDoctorsBySpeciality(String departmentId);
}
