package vn.edu.iuh.fit.service.impl;

import lombok.RequiredArgsConstructor;
import vn.edu.iuh.fit.model.Product;
import vn.edu.iuh.fit.repository.ProductRepository;
import vn.edu.iuh.fit.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean createSupplierCompanyNameRangeIndex() {
        return productRepository.createSupplierCompanyNameRangeIndex();
    }

    @Override
    public List<Product> listProductsBySupplier(String companyName, int page, int size) {
        if (companyName == null || companyName.trim().isEmpty()) {
            throw new IllegalArgumentException("companyName không được null hoặc rỗng");
        }
        if (page < 1) {
            throw new IllegalArgumentException("page phải >= 1");
        }
        if (size < 1) {
            throw new IllegalArgumentException("size phải >= 1");
        }

        return productRepository.listProductsBySupplier(companyName.trim(), page, size);
    }
}
