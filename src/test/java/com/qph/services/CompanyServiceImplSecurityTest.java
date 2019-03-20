package com.qph.services;

import com.qph.beans.Company;
import com.qph.repository.CompanyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CompanyServiceImplSecurityTest {
    @Autowired
    private CompanyServiceImpl companyService;

    @MockBean
    private CompanyRepository companyRepository;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testGetByIdWithoutAuthentication() {
        // GIVEN
        // WHEN
        companyService.get(11L);
        // THEN
    }

    @Test
    @WithMockUser(value = "reader", authorities = "ROLE_COMPANY_READ")
    public void testGetById() {
        // GIVEN
        // WHEN
        companyService.get(11L);
        // THEN
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testGetByNameWithoutAuth() {
        // GIVEN
        // WHEN
        companyService.get("Cname");
        // THEN
    }

    @Test
    @WithMockUser(value = "reader", roles = "COMPANY_READ")
    public void testGetByName() {
        // GIVEN
        // WHEN
        companyService.get("Cname");
        // THEN
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testCreateCompanyWithoutAuth() {
        // GIVEN
        // WHEN
        companyService.create(new Company());
        // THEN
    }

    @Test
    @WithMockUser(value = "creator", authorities = "COMPANY_CREATE")
    public void testCreateCompany() {
        // GIVEN
        // WHEN
        companyService.create(new Company());
        // THEN
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testUpdateCompanyWithoutAuth() {
        // GIVEN
        // WHEN
        companyService.update(new Company());
        // THEN
    }

    @Test
    @WithMockUser(value = "creator", authorities = "COMPANY_UPDATE")
    public void testUpdateCompany() {
        // GIVEN
        // WHEN
        companyService.update(new Company());
        // THEN
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testDeleteCompanyWithoutAuth() {
        // GIVEN
        // WHEN
        companyService.delete(11L);
        // THEN
    }

    @Test
    @WithMockUser(value = "creator", authorities = "COMPANY_DELETE")
    public void testDeleteCompany() {
        // GIVEN
        // WHEN
        companyService.delete(11L);
        // THEN
    }
}