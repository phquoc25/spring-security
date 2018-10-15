package com.qph.services;

import com.qph.beans.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> getAll();
    Optional<Company> get(Long id);
    Optional<Company> get(String name);
    Company create(Company company);
    Company update(Company company);
    void delete(Long id);
}
