package com.qph.repository;

import com.qph.beans.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
}