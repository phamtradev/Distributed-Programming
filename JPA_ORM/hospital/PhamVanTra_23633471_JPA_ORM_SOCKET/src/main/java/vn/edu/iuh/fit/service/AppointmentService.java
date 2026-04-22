package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.dto.AppointmentDTO;
import vn.edu.iuh.fit.dto.DoctorWorkloadDTO;

import java.util.List;

public interface AppointmentService {
    boolean addAppointment(AppointmentDTO dto);
    List<AppointmentDTO> getAppointmentDetails();
    List<DoctorWorkloadDTO> getDoctorWorkload();
}