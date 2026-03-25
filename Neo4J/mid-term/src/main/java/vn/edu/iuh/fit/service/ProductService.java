package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.model.Product;

import java.util.List;

public interface ProductService {

    boolean createSupplierCompanyNameRangeIndex();

    List<Product> listProductsBySupplier(String companyName, int page, int size);

}
