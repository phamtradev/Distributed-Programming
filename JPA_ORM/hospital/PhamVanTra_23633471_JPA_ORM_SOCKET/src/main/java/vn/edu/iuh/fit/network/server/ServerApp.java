package vn.edu.iuh.fit.network.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 3471;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server dang chay tai host=" + host + ", port=" + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client da ket noi: " + socket.getInetAddress());
                new Thread(new ClientHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}