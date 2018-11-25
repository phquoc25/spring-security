package com.qph.repository;

import com.qph.beans.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void test_addCompany_then_findByName() {
        // GIVEN
        String companyName = "a company";
        Company company = new Company();
        company.setName(companyName);
        // WHEN
        companyRepository.save(company);
        Optional<Company> actualResult = companyRepository.findByName(companyName);
        // THEN
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(company1 -> {
            assertNotNull(company1.getId());
            assertEquals(companyName, company1.getName());
        });
    }

    @Test
    public void test_addCompany_then_findById() {
        // GIVEN
        String companyName = "a company";
        Company company = new Company();
        company.setName(companyName);
        // WHEN
        Company savedCompany = companyRepository.save(company);
        Long savedCompanyId = savedCompany.getId();
        Optional<Company> actualResult = companyRepository.findById(savedCompanyId);
        // THEN
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(company1 -> {
            assertEquals(company1.getId(), savedCompanyId);
            assertEquals(companyName, company1.getName());
        });
    }

    @Test
    public void testUpdateCompany()
    {
        // GIVEN
        String companyName = "a company";
        String newName = "new company name";
        Company company = new Company();
        company.setName(companyName);

        // WHEN
        companyRepository.save(company);

        company.setName(newName);
        Company updatedCompany = companyRepository.save(company);
        // THEN
        assertEquals(newName, updatedCompany.getName());
    }

    @Test
    public void testDeleteCompany() {
        // GIVEN
        String companyName = "a company";
        Company company = new Company();
        company.setName(companyName);
        Company createdCompany = companyRepository.save(company);

        // WHEN
        Long createdCompanyId = createdCompany.getId();
        companyRepository.deleteById(createdCompanyId);

        // THEN
        Optional<Company> resultCompany = companyRepository.findById(createdCompanyId);
        assertFalse(resultCompany.isPresent());
    }
}