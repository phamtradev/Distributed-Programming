package vn.edu.iuh.fit.repository.impl;

import vn.edu.iuh.fit.model.Company;
import vn.edu.iuh.fit.repository.CompanyRepository;

public class CompanyRepositoryImpl extends GenericRepositoryImpl<Company, String> implements CompanyRepository {
    public CompanyRepositoryImpl(Class<Company> entityClass) {
        super(entityClass);
    }
}
