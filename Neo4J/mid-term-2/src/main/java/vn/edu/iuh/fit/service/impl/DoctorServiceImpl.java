package vn.edu.iuh.fit.service.impl;

import vn.edu.iuh.fit.model.Doctor;
import vn.edu.iuh.fit.repository.DoctorRepository;
import vn.edu.iuh.fit.service.DoctorService;

import java.util.Map;

public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor findDoctorById(String doctorId) {
        if (doctorId == null || doctorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor Id ko dc trong");
        }
        return doctorRepository.findDoctorById(doctorId);
    }

    @Override
    public Map<String, Long> getNoOfDoctorsBySpeciality(String departmentId) {
        if (departmentId == null || departmentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Department Id ko dc trong");
        }
        return doctorRepository.getNoOfDoctorsBySpeciality(departmentId);
    }
}
