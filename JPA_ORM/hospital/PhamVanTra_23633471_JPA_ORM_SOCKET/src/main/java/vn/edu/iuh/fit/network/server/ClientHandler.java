package vn.edu.iuh.fit.network.server;

import vn.edu.iuh.fit.dto.AppointmentDTO;
import vn.edu.iuh.fit.dto.Request;
import vn.edu.iuh.fit.dto.Response;
import vn.edu.iuh.fit.service.AppointmentService;
import vn.edu.iuh.fit.service.impl.AppointmentServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final AppointmentService service = new AppointmentServiceImpl();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())
        ) {
            while (true) {
                Object obj = ois.readObject();
                if (!(obj instanceof Request request)) break;

                Response response;

                switch (request.getAction()) {
                    case "ADD_APPOINTMENT" -> {
                        boolean ok = service.addAppointment((AppointmentDTO) request.getData());
                        response = new Response(ok, ok ? "Them lich kham thanh cong" : "Them lich kham that bai", null);
                    }
                    case "GET_APPOINTMENT_DETAILS" -> {
                        response = new Response(true, "Lay danh sach thanh cong",
                                service.getAppointmentDetails());
                    }
                    case "GET_DOCTOR_WORKLOAD" -> {
                        response = new Response(true, "Lay thong ke thanh cong",
                                service.getDoctorWorkload());
                    }
                    default -> response = new Response(false, "Action khong hop le", null);
                }

                oos.writeObject(response);
                oos.flush();
            }
        } catch (Exception e) {
            System.out.println("Client ngat ket noi");
        }
    }
}