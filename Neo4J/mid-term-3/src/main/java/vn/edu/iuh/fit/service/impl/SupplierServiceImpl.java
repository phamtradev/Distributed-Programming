package vn.edu.iuh.fit.service.impl;

import vn.edu.iuh.fit.dto.ProductsBySupplierDTO;
import vn.edu.iuh.fit.dto.UpdateSupplierDTO;
import vn.edu.iuh.fit.model.Product;
import vn.edu.iuh.fit.model.Supplier;
import vn.edu.iuh.fit.repository.SupplierRepository;
import vn.edu.iuh.fit.service.SupplierService;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public boolean createRangeIndexOnCompanyName() {
        return supplierRepository.createRangeIndexOnCompanyName();
    }

    @Override
    public List<Product> listProductsBySupplier(ProductsBySupplierDTO request) {
        if (request.getCompanyName() == null || request.getCompanyName().trim().isEmpty()) {
            throw new IllegalArgumentException("Company name ko dc null hoac rong");
        }
        if (request.getPage() < 1) {
            throw new IllegalArgumentException("page phải >= 1");
        }
        if (request.getSize() < 1) {
            throw new IllegalArgumentException("size phải >= 1");
        }
        return supplierRepository.listProductsBySupplier(
                request.getCompanyName(),
                request.getPage(),
                request.getSize()
        );
    }

    @Override
    public boolean updateSupplier(UpdateSupplierDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Supplier ko dc null");
        }
        if (request.getSupplierID() == null || request.getSupplierID().trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier id ko dc null hoac rong");
        }
        if (request.getCompanyName() == null || request.getCompanyName().trim().isEmpty()) {
            throw new IllegalArgumentException("Company name ko dc null hoac rong");
        }
        Supplier supplier = Supplier
                .builder()
                .supplierID(request.getSupplierID())
                .country(request.getCountry())
                .contactName(request.getContactName())
                .companyName(request.getCompanyName())
                .build();
        return supplierRepository.updateSupplier(supplier);
    }
}
