package vn.edu.iuh.fit.service.impl;

import vn.edu.iuh.fit.model.Doctor;
import vn.edu.iuh.fit.repository.DoctorRepository;
import vn.edu.iuh.fit.service.DoctorService;

import java.util.List;
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
    public Map<String, Long> getNoOfDoctorBySpeciality(String departmentName) {
        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Department Name ko dc trong");
        }
        return doctorRepository.getNoOfDoctorBySpeciality(departmentName);
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        if (doctor.getName() == null || doctor.getDoctorId() == null) {
            throw new IllegalArgumentException("Name va id ko dc trong");
        }
        return doctorRepository.addDoctor(doctor);
    }

    @Override
    public List<Doctor> listDoctorBySpeciality(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Key word ko dc trong");
        }
        return doctorRepository.listDoctorBySpeciality(keyword);
    }


}
