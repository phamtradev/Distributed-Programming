package vn.edu.iuh.fit.repository;

import vn.edu.iuh.fit.model.Doctor;

import java.util.List;
import java.util.Map;

public interface DoctorRepository {

    Doctor findDoctorById(String doctorId);

    Map<String, Long> getNoOfDoctorBySpeciality(String departmentName);

    boolean addDoctor(Doctor doctor);

    List<Doctor> listDoctorBySpeciality(String keyword);

    boolean updateDiagnosis(String doctorId, String patientId, String newDiagnosis);
}