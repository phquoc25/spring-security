package com.qph.resources;

import com.qph.beans.Company;
import com.qph.services.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerTest {
    @Mock
    private CompanyService companyService;

    private CompanyController companyController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        companyController = new CompanyController(companyService);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }

    @Test
    public void create() {

    }

    @Test
    public void testGetByIdApi() throws Exception {
        // GIVEN
        long companyId = 100;
        Company expectedCompany = new Company();
        expectedCompany.setId(companyId);
        expectedCompany.setName("aName");
        doReturn(Optional.of(expectedCompany)).when(companyService).get(companyId);

        // WHEN
        mockMvc.perform(get("/secured/company/" + companyId))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:100,name:aName}"));
        // THEN
        verify(companyService).get(companyId);
    }

    @Test
    public void getByName() {
    }
}