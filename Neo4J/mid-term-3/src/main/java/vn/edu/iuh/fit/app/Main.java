package vn.edu.iuh.fit.app;

import vn.edu.iuh.fit.dto.ProductsBySupplierDTO;
import vn.edu.iuh.fit.dto.UpdateSupplierDTO;
import vn.edu.iuh.fit.model.Product;
import vn.edu.iuh.fit.model.Supplier;
import vn.edu.iuh.fit.repository.OrderRepository;
import vn.edu.iuh.fit.repository.SupplierRepository;
import vn.edu.iuh.fit.repository.impl.OrderRepositoryImpl;
import vn.edu.iuh.fit.repository.impl.SupplierRepositoryImpl;
import vn.edu.iuh.fit.service.OrderService;
import vn.edu.iuh.fit.service.SupplierService;
import vn.edu.iuh.fit.service.impl.OrderServiceImpl;
import vn.edu.iuh.fit.service.impl.SupplierServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SupplierRepository supplierRepository = new SupplierRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();
        SupplierService supplierService = new SupplierServiceImpl(supplierRepository);
        OrderService orderService = new OrderServiceImpl(orderRepository);

        try {
            System.out.println("TAO RANGE INDEX");
            boolean created = supplierService.createRangeIndexOnCompanyName();
            if (created) {
                System.out.println("TAO RANGE INDEX THANH CONG");
            } else {
                System.out.println("THAT BAI");
            }

            System.out.println("THONG KE DANH SACH SAN PHAM CUA MOT NHA CUNG CAP");
            ProductsBySupplierDTO result = ProductsBySupplierDTO
                    .builder()
                    .companyName("New Orleans Cajun Delights")
                    .size(5)
                    .page(1)
                    .build();

            List<Product> products = supplierService.listProductsBySupplier(result);
            products.forEach(System.out::println);

            System.out.println("CAP NHAT SUPPLIER");
            UpdateSupplierDTO updateSupplierDTO = UpdateSupplierDTO
                    .builder()
                    .supplierID("S003")
                    .companyName("KAKAKA")
                    .contactName("KAKAKA")
                    .country("KAKAKA")
                    .build();
            boolean updated = supplierService.updateSupplier(updateSupplierDTO);
            System.out.println(updated);

            System.out.println("TINH TONG TIEN DON HANG");
            String orderId = "O005";
            double results = orderService.calculateTotalOrder(orderId);
            System.out.println(results);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
