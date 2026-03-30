package vn.edu.iuh.fit.app;

import vn.edu.iuh.fit.model.Doctor;
import vn.edu.iuh.fit.repository.DoctorRepository;
import vn.edu.iuh.fit.repository.impl.DoctorRepositoryImpl;
import vn.edu.iuh.fit.service.DoctorService;
import vn.edu.iuh.fit.service.impl.DoctorServiceImpl;

import javax.print.Doc;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DoctorRepository doctorRepository = new DoctorRepositoryImpl();

        DoctorService doctorService = new DoctorServiceImpl(doctorRepository);
        System.out.println("1. Tim Doctor theo ID");
        System.out.println("2. Thong ke bac si theo chuyen khoa");
        System.out.println("3. Them Doctor moi");
        System.out.println("0. Thoat");
        System.out.print("Chon chuc nang: ");

        try {
            System.out.println("TIM DOCTOR THEO ID");
            String doctorId = "DR.017";
            Doctor doctor = doctorService.findDoctorById(doctorId);
            if (doctor != null) {
                System.out.println("KET QUA: " + doctor);
            } else {
                System.out.println("KO TIM THAY");
            }


            System.out.println("THONG KE SO BAC SI THEO TUNG CHUYEN KHOA CUA MOT KHOA");
            String departmentName = "Internal Medicine";
            Map<String, Long> result = doctorService.getNoOfDoctorBySpeciality(departmentName);
            if (result.isEmpty()) {
                System.out.println("KO CO DU LIEU CHO KHOA NAY");
            } else {
                System.out.println("THONG KE: ");
                result.forEach((k, v) -> {
                    System.out.println(k + ":" + v);
                });
            }

            System.out.println("THEM DOCTOR MOI");
            Doctor newDoctor = Doctor
                    .builder()
                    .doctorId("DR.099")
                    .name("AHIHI")
                    .phone("09876543322")
                    .departmentId("DP.099")
                    .speciality("HIHI")
                    .build();

            doctorService.addDoctor(newDoctor);
            System.out.println("--- THEM DOCTOR THANH CONG! ---");
            System.out.println(newDoctor);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}