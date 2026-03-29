package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.model.Doctor;

public interface DoctorService {

    Doctor findDoctorById(String doctorId);
}
