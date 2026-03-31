package vn.edu.iuh.fit.app;

import vn.edu.iuh.fit.dto.ProductsBySupplierDTO;
import vn.edu.iuh.fit.dto.UpdateSupplierDTO;
import vn.edu.iuh.fit.model.Product;
import vn.edu.iuh.fit.repository.OrderRepository;
import vn.edu.iuh.fit.repository.SupplierRepository;
import vn.edu.iuh.fit.repository.impl.OrderRepositoryImpl;
import vn.edu.iuh.fit.repository.impl.SupplierRepositoryImpl;
import vn.edu.iuh.fit.service.OrderService;
import vn.edu.iuh.fit.service.SupplierService;
import vn.edu.iuh.fit.service.impl.OrderServiceImpl;
import vn.edu.iuh.fit.service.impl.SupplierServiceImpl;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SupplierRepository supplierRepository = new SupplierRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();
        SupplierService supplierService = new SupplierServiceImpl(supplierRepository);
        OrderService orderService = new OrderServiceImpl(orderRepository);

        try {
            System.out.println("TAO RANGE INDEX CHO COMPANY NAME");
            boolean createdIndex = supplierService.createRangeIndexOnCompanyName();
            if (createdIndex) {
                System.out.println("TAO RANGE INDEX THANH CONG");
            } else {
                System.out.println("TAO RANGE INDEX THAT BAI");
            }

            System.out.println("LIET KE SAN PHAM THEO NHA CUNG CAP");
            ProductsBySupplierDTO result = ProductsBySupplierDTO
                    .builder()
                    .companyName("Specialty Biscuits Ltd.")
                    .page(1)
                    .size(5)
                    .build();
            List<Product> products = supplierService.listProductsBySupplier(result);
            products.forEach(System.out::println);

            System.out.println("CAP NHAT THONG TIN NHA CUNG CAP");
            UpdateSupplierDTO updateSupplierDTO = UpdateSupplierDTO
                    .builder()
                    .supplierID("S001")
                    .companyName("KAKAKA")
                    .contactName("HUHUHUA")
                    .country("HYHYHY")
                    .build();

            boolean updated = supplierService.updateSupplier(updateSupplierDTO);
            System.out.println(updated);

            System.out.println("TINH TONG TIEN DON HANG");
            String orderID = "O008";
            double totalAmount = orderRepository.calculateTotalOrder(orderID);
            System.out.println(totalAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
