package vn.edu.iuh.fit.app;

import vn.edu.iuh.fit.db.ConnectDB;
import vn.edu.iuh.fit.model.Supplier;
import vn.edu.iuh.fit.repository.OrderRepository;
import vn.edu.iuh.fit.repository.ProductRepository;
import vn.edu.iuh.fit.repository.SupplierRepository;
import vn.edu.iuh.fit.service.OrderService;
import vn.edu.iuh.fit.service.ProductService;
import vn.edu.iuh.fit.service.SupplierService;
import vn.edu.iuh.fit.service.impl.OrderServiceImpl;
import vn.edu.iuh.fit.service.impl.ProductServiceImpl;
import vn.edu.iuh.fit.service.impl.SupplierServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepository();
        SupplierRepository supplierRepository = new SupplierRepository();
        OrderRepository orderRepository = new OrderRepository();

        ProductService productService = new ProductServiceImpl(productRepository);
        SupplierService supplierService = new SupplierServiceImpl(supplierRepository);
        OrderService orderService = new OrderServiceImpl(orderRepository);

        try {
            System.out.println("=== 1. Tạo index ===");
            boolean created = productService.createSupplierCompanyNameRangeIndex();
            System.out.println("Tạo index thành công: " + created);

            System.out.println("\n=== 2. Liệt kê sản phẩm theo nhà cung cấp ===");
            productService.listProductsBySupplier("Exotic Liquids", 1, 5)
                    .forEach(System.out::println);

            System.out.println("\n=== 3. Cập nhật supplier ===");
            Supplier supplier = new Supplier(
                    "S002",
                    "Exotic Liquids",
                    "Exotic Liquids Updated",
                    "Charlotte Cooper",
                    "UK"
            );
            boolean updated = supplierService.updateSupplier(supplier);
            System.out.println("Cập nhật thành công: " + updated);

            System.out.println("\n=== 4. Tính tổng tiền đơn hàng ===");
            double total = orderService.calculateTotalOrder("O008");
            System.out.println("Tổng tiền đơn hàng O008: " + total);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}