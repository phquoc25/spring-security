package com.qph.services;

import com.qph.beans.Company;
import com.qph.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAll() {
        return (List<Company>) companyRepository.findAll();
    }

    @Override
    public Optional<Company> get(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Optional<Company> get(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company update(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
