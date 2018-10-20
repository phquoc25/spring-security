package com.qph.services;

import com.qph.beans.Company;
import com.qph.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompanyServiceImplTest {
    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    public void testGetById() {
        // GIVEN
        String companyName = "company name";
        Company expectedCompany = new Company();
        long id = 11L;
        expectedCompany.setId(id);
        expectedCompany.setName(companyName);

        doReturn(Optional.of(expectedCompany)).when(companyRepository).findById(id);
        // WHEN
        Optional<Company> actualResult = companyService.get(id);
        // THEN
        verify(companyRepository).findById(id);
        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(company -> assertEquals(expectedCompany, company));
    }

    @Test
    public void testCreateCompany() {
        // GIVEN
        String companyName = "company name";
        Company company = new Company();
        company.setName(companyName);

        Company expectedCompany = new Company();
        expectedCompany.setId(11L);
        expectedCompany.setName(companyName);

        doReturn(expectedCompany).when(companyRepository).save(company);
        // WHEN
        Company actualResult = companyService.create(company);
        // THEN
        verify(companyRepository).save(company);
        assertEquals(expectedCompany, actualResult);
    }
}