package vn.edu.iuh.fit.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.iuh.fit.model.Appointment;
import vn.edu.iuh.fit.repository.AppointmentRepository;
import vn.edu.iuh.fit.util.JPAUtils;

import java.util.List;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    @Override
    public boolean addAppointment(Appointment appointment) {
        EntityManager em = JPAUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(appointment);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Object[]> getAppointmentDetails() {
        EntityManager em = JPAUtils.getEntityManager();
        try {
            String jpql = """
                    select d.id, d.fullName, p.id, p.fullName, a.id.appointmentTime, a.status
                    from Appointment a
                    join a.doctor d
                    join a.patient p
                    order by a.id.appointmentTime
                    """;
            return em.createQuery(jpql, Object[].class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Object[]> getDoctorWorkload() {
        EntityManager em = JPAUtils.getEntityManager();
        try {
            String jpql = """
                    select d.id, d.fullName, function('date', a.id.appointmentTime), count(a)
                    from Appointment a
                    join a.doctor d
                    group by d.id, d.fullName, function('date', a.id.appointmentTime)
                    having count(a) >= 2
                    order by function('date', a.id.appointmentTime)
                    """;
            return em.createQuery(jpql, Object[].class).getResultList();
        } finally {
            em.close();
        }
    }
}