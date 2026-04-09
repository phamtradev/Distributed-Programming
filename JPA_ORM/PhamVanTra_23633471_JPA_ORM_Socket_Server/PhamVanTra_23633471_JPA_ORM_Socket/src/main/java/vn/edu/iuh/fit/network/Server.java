package vn.edu.iuh.fit.network;

import vn.edu.iuh.fit.service.JobService;
import vn.edu.iuh.fit.service.impl.JobServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) throws Exception {

        Context context = new InitialContext();
        LocateRegistry.createRegistry(3471);

        JobService jobService = new JobServiceImpl();

        context.bind("rmi://PhamTra:3471/jobService", jobService);

        System.out.println("SERVER START");
    }
}
