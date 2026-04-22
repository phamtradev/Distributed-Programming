package vn.edu.iuh.fit.repository;

import vn.edu.iuh.fit.model.Appointment;

import java.util.List;

public interface AppointmentRepository {
    boolean addAppointment(Appointment appointment);
    List<Object[]> getAppointmentDetails();
    List<Object[]> getDoctorWorkload();
}