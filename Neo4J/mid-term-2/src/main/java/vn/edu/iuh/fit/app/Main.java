package vn.edu.iuh.fit.app;

import vn.edu.iuh.fit.model.Doctor;
import vn.edu.iuh.fit.repository.DoctorRepository;
import vn.edu.iuh.fit.service.DoctorService;
import vn.edu.iuh.fit.service.impl.DoctorServiceImpl;

import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DoctorRepository doctorRepository = new DoctorRepository();

        DoctorServiceImpl doctorServiceImpl = new DoctorServiceImpl(doctorRepository);

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("TIM DOCTOR THEO ID");
            System.out.println("NHAP ID DOCTOR: ");
            String doctorId = scanner.nextLine();
            Doctor doctor = doctorServiceImpl.findDoctorById(doctorId);
            System.out.println(doctor);

            System.out.println("THONG KE SO BAC SI THEO TUNG CHUYEN KHOA CUA MOT KHOA");
            System.out.println("NHAP DEPARTMENT NAME: ");
            String departmentName = scanner.nextLine();
            Map<String, Long> result = doctorServiceImpl.getNoOfDoctorBySpeciality(departmentName);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}