package vn.edu.iuh.fit.service.impl;

import jakarta.persistence.EntityManager;
import vn.edu.iuh.fit.constant.Status;
import vn.edu.iuh.fit.dto.AppointmentDTO;
import vn.edu.iuh.fit.dto.DoctorWorkloadDTO;
import vn.edu.iuh.fit.model.*;
import vn.edu.iuh.fit.repository.AppointmentRepository;
import vn.edu.iuh.fit.repository.impl.AppointmentRepositoryImpl;
import vn.edu.iuh.fit.service.AppointmentService;
import vn.edu.iuh.fit.util.JPAUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository = new AppointmentRepositoryImpl();

    @Override
    public boolean addAppointment(AppointmentDTO dto) {
        EntityManager em = JPAUtils.getEntityManager();
        try {
            Doctor doctor = em.find(Doctor.class, dto.getDoctorId());
            Patient patient = em.find(Patient.class, dto.getPatientId());

            if (doctor == null || patient == null) {
                return false;
            }

            AppointmentId id = new AppointmentId(
                    dto.getDoctorId(),
                    dto.getPatientId(),
                    dto.getAppointmentTime()
            );

            Appointment appointment = new Appointment();
            appointment.setId(id);
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointment.setStatus(Status.valueOf(dto.getStatus()));

            return repository.addAppointment(appointment);
        } finally {
            em.close();
        }
    }

    @Override
    public List<AppointmentDTO> getAppointmentDetails() {
        List<Object[]> rows = repository.getAppointmentDetails();
        List<AppointmentDTO> result = new ArrayList<>();

        for (Object[] row : rows) {
            AppointmentDTO dto = new AppointmentDTO();
            dto.setDoctorId((String) row[0]);
            dto.setDoctorName((String) row[1]);
            dto.setPatientId((String) row[2]);
            dto.setPatientName((String) row[3]);
            dto.setAppointmentTime((java.time.LocalDateTime) row[4]);
            dto.setStatus(row[5].toString());
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<DoctorWorkloadDTO> getDoctorWorkload() {
        List<Object[]> rows = repository.getDoctorWorkload();
        List<DoctorWorkloadDTO> result = new ArrayList<>();

        for (Object[] row : rows) {
            LocalDate date;
            Object rawDate = row[2];

            if (rawDate instanceof Date sqlDate) {
                date = sqlDate.toLocalDate();
            } else if (rawDate instanceof LocalDate localDate) {
                date = localDate;
            } else {
                date = LocalDate.parse(rawDate.toString());
            }

            DoctorWorkloadDTO dto = new DoctorWorkloadDTO(
                    (String) row[0],
                    (String) row[1],
                    date,
                    (Long) row[3]
            );
            result.add(dto);
        }
        return result;
    }
}