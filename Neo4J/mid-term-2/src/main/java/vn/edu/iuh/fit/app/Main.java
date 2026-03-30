package vn.edu.iuh.fit.app;

import vn.edu.iuh.fit.model.Doctor;
import vn.edu.iuh.fit.repository.DoctorRepository;
import vn.edu.iuh.fit.service.DoctorService;
import vn.edu.iuh.fit.service.impl.DoctorServiceImpl;

import javax.print.Doc;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DoctorRepository doctorRepository = new DoctorRepository();

        DoctorServiceImpl doctorServiceImpl = new DoctorServiceImpl(doctorRepository);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Tim Doctor theo ID");
            System.out.println("2. Thong ke bac si theo chuyen khoa");
            System.out.println("3. Them Doctor moi");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");

            int choice = Integer.parseInt(scanner.nextLine());

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("NHAP ID DOCTOR: ");
                        String doctorId = scanner.nextLine();
                        Doctor doctor = doctorServiceImpl.findDoctorById(doctorId);
                        if (doctor != null) {
                            System.out.println("KET QUA: " + doctor);
                        } else {
                            System.out.println("KO TIM THAY");
                        }
                    }
                    case 2 -> {
                        System.out.println("THONG KE SO BAC SI THEO TUNG CHUYEN KHOA CUA MOT KHOA");
                        System.out.print("NHAP DEPARTMENT NAME: ");
                        String departmentName = scanner.nextLine();
                        Map<String, Long> result = doctorServiceImpl.getNoOfDoctorBySpeciality(departmentName);
                        if (result.isEmpty()) {
                            System.out.println("KO CO DU LIEU CHO KHOA NAY");
                        } else {
                            System.out.println("THONG KE: ");
                            result.forEach((k, v) -> {
                                System.out.println(k + ":" + v);
                            });
                        }
                    }
                    case 3 -> {
                        System.out.println("THEM DOCTOR MOI");
                        System.out.println("DoctorId: ");
                        String id = scanner.nextLine();
                        System.out.println("Doctor Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Doctor Phone: ");
                        String phone = scanner.nextLine();
                        System.out.println("DepartmentId: ");
                        String departmentId = scanner.nextLine();
                        System.out.println("Speciality: ");
                        String speciality = scanner.nextLine();

                        Doctor newDoctor = Doctor
                                .builder()
                                .doctorId(id)
                                .name(name)
                                .phone(phone)
                                .departmentId(departmentId)
                                .speciality(speciality)
                                .build();

                        doctorServiceImpl.addDoctor(newDoctor);
                        System.out.println("--- THEM DOCTOR THANH CONG! ---");
                    }
                    case 0 -> {
                        System.exit(0);
                    }
                    default -> System.out.println("LUA CHON KO HOP LE");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}