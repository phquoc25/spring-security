package com.qph.repository;

import com.qph.beans.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    Optional<Company> findByName(String name);
}
