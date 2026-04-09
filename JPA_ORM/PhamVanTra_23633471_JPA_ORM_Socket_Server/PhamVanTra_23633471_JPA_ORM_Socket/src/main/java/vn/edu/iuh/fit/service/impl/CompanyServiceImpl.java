package vn.edu.iuh.fit.service.impl;

import vn.edu.iuh.fit.dto.CompanyDTO;
import vn.edu.iuh.fit.mapper.JacksonDataMapper;
import vn.edu.iuh.fit.model.Company;
import vn.edu.iuh.fit.repository.impl.CompanyRepositoryImpl;
import vn.edu.iuh.fit.service.CompanyService;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepositoryImpl companyRepository = new CompanyRepositoryImpl(Company.class);
    private final JacksonDataMapper mapper = new JacksonDataMapper();

    @Override
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.loadAll().stream()
                .map(company -> mapper.toObject(mapper.toMap(company), CompanyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDTO getCompanyById(String id) {
        Company company = companyRepository.findById(id);
        return mapper.toObject(mapper.toMap(company), CompanyDTO.class);
    }

    @Override
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        Company company = mapper.toObject(mapper.toMap(companyDTO), Company.class);
        Company saved = companyRepository.create(company);
        return mapper.toObject(mapper.toMap(saved), CompanyDTO.class);
    }

    @Override
    public void deleteCompany(String id) {
        companyRepository.delete(id);
    }
}
