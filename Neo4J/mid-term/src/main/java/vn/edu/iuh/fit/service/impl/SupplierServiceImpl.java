package vn.edu.iuh.fit.service.impl;

import lombok.RequiredArgsConstructor;
import vn.edu.iuh.fit.model.Supplier;
import vn.edu.iuh.fit.repository.SupplierRepository;
import vn.edu.iuh.fit.service.SupplierService;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        if (supplier == null) {
            throw new IllegalArgumentException("supplier không được null");
        }
        if (supplier.getSupplierId() == null || supplier.getSupplierId().trim().isEmpty()) {
            throw new IllegalArgumentException("SupplierID không được null hoặc rỗng");
        }
        if (supplier.getCompanyName() == null || supplier.getCompanyName().trim().isEmpty()) {
            throw new IllegalArgumentException("CompanyName không được null hoặc rỗng");
        }

        return supplierRepository.updateSupplier(supplier);
    }
}
