package vn.edu.iuh.fit.service.impl;

import vn.edu.iuh.fit.dto.CreateDoctorDTO;
import vn.edu.iuh.fit.dto.DoctorDTO;
import vn.edu.iuh.fit.mapper.GenericDataMapper;
import vn.edu.iuh.fit.model.Doctor;
import vn.edu.iuh.fit.repository.DoctorRepository;
import vn.edu.iuh.fit.service.DoctorService;

import java.util.List;
import java.util.Map;

public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final GenericDataMapper mapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, GenericDataMapper mapper) {
        this.doctorRepository = doctorRepository;
        this.mapper = mapper;
    }


    @Override
    public DoctorDTO findDoctorById(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Id ko dc null");
        }
        Doctor doctor = doctorRepository.findDoctorById(id);
        return mapper.toObject(mapper.toMap(doctor), DoctorDTO.class);
    }

    @Override
    public Map<String, Long> getNoOfDoctorBySpeciality(String departmentName) {
        if (departmentName == null || departmentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Department Name ko dc trong");
        }
        return doctorRepository.getNoOfDoctorBySpeciality(departmentName);
    }

    @Override
    public boolean addDoctor(CreateDoctorDTO createDoctorDTO) {
        if (createDoctorDTO.getName() == null || createDoctorDTO.getDoctorId() == null) {
            throw new IllegalArgumentException("Name va id ko dc trong");
        }
        Doctor doctor = mapper.toObject(mapper.toMap(createDoctorDTO), Doctor.class);

        return doctorRepository.addDoctor(doctor);
    }

    @Override
    public List<Doctor> listDoctorBySpeciality(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Key word ko dc trong");
        }
        return doctorRepository.listDoctorBySpeciality(keyword);
    }

    @Override
    public boolean updateDiagnosis(String doctorId, String patientId, String newDiagnosis) {
        if (doctorId == null || patientId == null || newDiagnosis == null) {
            throw new IllegalArgumentException("Doctor id ko dc trong");
        }
        return doctorRepository.updateDiagnosis(doctorId, patientId, newDiagnosis);
    }


}
