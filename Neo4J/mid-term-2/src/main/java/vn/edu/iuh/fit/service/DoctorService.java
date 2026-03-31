package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.dto.CreateDoctorDTO;
import vn.edu.iuh.fit.dto.DoctorDTO;
import vn.edu.iuh.fit.model.Doctor;

import java.util.List;
import java.util.Map;

public interface DoctorService {

    DoctorDTO findDoctorById(String doctorId);

    Map<String, Long> getNoOfDoctorBySpeciality(String departmentName);

    boolean addDoctor(CreateDoctorDTO createDoctorDTO);

    List<Doctor> listDoctorBySpeciality(String keyword);

    boolean updateDiagnosis(String doctorId, String patientId, String newDiagnosis);

}
