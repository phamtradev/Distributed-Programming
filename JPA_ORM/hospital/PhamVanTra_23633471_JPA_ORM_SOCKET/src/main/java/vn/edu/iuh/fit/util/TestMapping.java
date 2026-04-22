package vn.edu.iuh.fit.util;

import jakarta.persistence.EntityManager;

public class TestMapping {
    public static void main(String[] args) {
        EntityManager em = null;
        try {
            em = JPAUtils.getEntityManager();
            System.out.println("Ket noi thanh cong, JPA da anh xa entity!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            JPAUtils.close();
        }
    }
}