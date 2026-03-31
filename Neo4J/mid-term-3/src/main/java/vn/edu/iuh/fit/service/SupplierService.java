package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.dto.ProductsBySupplierDTO;
import vn.edu.iuh.fit.dto.UpdateSupplierDTO;
import vn.edu.iuh.fit.model.Product;
import vn.edu.iuh.fit.model.Supplier;

import java.util.List;

public interface SupplierService {

    boolean createRangeIndexOnCompanyName();

    List<Product> listProductsBySupplier(ProductsBySupplierDTO request);

    boolean updateSupplier(UpdateSupplierDTO updateSupplierDTO);
}
