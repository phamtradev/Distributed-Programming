package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    List<CompanyDTO> getAllCompanies();

    CompanyDTO getCompanyById(String id);

    CompanyDTO saveCompany(CompanyDTO companyDTO);

    void deleteCompany(String id);
}