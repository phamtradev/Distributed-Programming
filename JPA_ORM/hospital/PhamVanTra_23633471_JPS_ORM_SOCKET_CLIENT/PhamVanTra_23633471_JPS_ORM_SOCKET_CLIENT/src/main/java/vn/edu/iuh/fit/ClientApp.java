package vn.edu.iuh.fit;

import vn.edu.iuh.fit.dto.AppointmentDTO;
import vn.edu.iuh.fit.dto.Request;
import vn.edu.iuh.fit.dto.Response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 3471;

        try (
                Socket socket = new Socket(host, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Scanner sc = new Scanner(System.in)
        ) {
            while (true) {
                System.out.println("\n===== MENU =====");
                System.out.println("1. Them lich kham");
                System.out.println("2. Liet ke lich kham");
                System.out.println("3. Liet ke bac si co tu 2 lich trong ngay");
                System.out.println("0. Thoat");
                System.out.print("Chon: ");
                int choice = Integer.parseInt(sc.nextLine());

                if (choice == 0) break;

                Request request = null;

                switch (choice) {
                    case 1 -> {
                        AppointmentDTO dto = new AppointmentDTO();
                        System.out.print("Ma bac si: ");
                        dto.setDoctorId(sc.nextLine());
                        System.out.print("Ma benh nhan: ");
                        dto.setPatientId(sc.nextLine());
                        System.out.print("Thoi gian (yyyy-MM-ddTHH:mm): ");
                        dto.setAppointmentTime(LocalDateTime.parse(sc.nextLine()));
                        System.out.print("Trang thai (PENDING/CONFIRMED/CANCELLED): ");
                        dto.setStatus(sc.nextLine());

                        request = new Request("ADD_APPOINTMENT", dto);
                    }
                    case 2 -> request = new Request("GET_APPOINTMENT_DETAILS", null);
                    case 3 -> request = new Request("GET_DOCTOR_WORKLOAD", null);
                    default -> System.out.println("Lua chon khong hop le");
                }

                if (request != null) {
                    oos.writeObject(request);
                    oos.flush();

                    Response response = (Response) ois.readObject();
                    System.out.println("Thong bao: " + response.getMessage());

                    if (response.getData() instanceof List<?> list) {
                        for (Object item : list) {
                            System.out.println(item);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}