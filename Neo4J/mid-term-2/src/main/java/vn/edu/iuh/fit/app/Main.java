package vn.edu.iuh.fit.app;

import vn.edu.iuh.fit.model.Doctor;
import vn.edu.iuh.fit.repository.DoctorRepository;
import vn.edu.iuh.fit.service.DoctorService;
import vn.edu.iuh.fit.service.impl.DoctorServiceImpl;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DoctorRepository doctorRepository = new DoctorRepository();

        DoctorService doctorService = new DoctorServiceImpl(doctorRepository);

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("TIM DOCTOR THEO ID");
            System.out.println("NHAP ID DOCTOR: ");
            String id = scanner.nextLine();
            Doctor doctor = doctorService.findDoctorById(id);
            System.out.println(doctor);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}