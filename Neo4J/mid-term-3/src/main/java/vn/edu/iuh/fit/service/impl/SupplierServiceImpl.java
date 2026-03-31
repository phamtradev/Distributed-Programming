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
        if (request == null) {
            throw new IllegalArgumentException("requestDTO không được null");
        }
        if (request.getCompanyName() == null || request.getCompanyName().trim().isEmpty()) {
            throw new IllegalArgumentException("companyName không được null hoặc rỗng");
        }
        if (request.getPage() < 1) {
            throw new IllegalArgumentException("page phải >= 1");
        }
        if (request.getSize() < 1) {
            throw new IllegalArgumentException("size phải >= 1");
        }
        return supplierRepository.listProductsBySupplier(
                request.getCompanyName().trim(),
                request.getPage(),
                request.getSize()
        );
    }

    @Override
    public boolean updateSupplier(UpdateSupplierDTO updateSupplierDTO) {
        if (updateSupplierDTO == null) {
            throw new IllegalArgumentException("dto không được null");
        }
        if (updateSupplierDTO.getSupplierID() == null || updateSupplierDTO.getSupplierID().trim().isEmpty()) {
            throw new IllegalArgumentException("SupplierID không được null hoặc rỗng");
        }
        if (updateSupplierDTO.getCompanyName() == null || updateSupplierDTO.getCompanyName().trim().isEmpty()) {
            throw new IllegalArgumentException("CompanyName không được null hoặc rỗng");
        }
        Supplier supplier = Supplier
                .builder()
                .supplierID(updateSupplierDTO.getSupplierID())
                .country(updateSupplierDTO.getCountry())
                .contactName(updateSupplierDTO.getContactName())
                .companyName(updateSupplierDTO.getCompanyName())
                .build();
        return supplierRepository.updateSupplier(supplier);
    }
}
