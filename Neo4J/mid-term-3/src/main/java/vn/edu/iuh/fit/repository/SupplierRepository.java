package vn.edu.iuh.fit.repository;

import vn.edu.iuh.fit.model.Product;
import vn.edu.iuh.fit.model.Supplier;

import java.util.List;

public interface SupplierRepository {

    boolean createRangeIndexOnCompanyName();

    List<Product> listProductsBySupplier(String companyName, int page, int size);

    boolean updateSupplier(Supplier supplier);
}
