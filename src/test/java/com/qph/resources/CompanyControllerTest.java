package com.qph.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qph.beans.Company;
import com.qph.services.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CompanyControllerTest {
    @Mock
    private CompanyService companyService;

    private CompanyController companyController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Before
    public void setUp()
    {
        companyController = new CompanyController(companyService);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateCompany() throws Exception {
        // GIVEN
        Company inputCompany = new Company();
        inputCompany.setName("aName");

        long companyId = 100;
        Company expectedCompany = new Company();
        expectedCompany.setId(companyId);
        expectedCompany.setName("aName");
        doReturn(expectedCompany).when(companyService).create(inputCompany);

        // WHEN
        mockMvc.perform(post("/secured/company")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(inputCompany))
                )
                .andExpect(status().isCreated());
        // THEN
        verify(companyService).create(inputCompany);
    }

    @Test
    public void testUpdateCompany() throws Exception {
        // GIVEN
        Company inputCompany = new Company();
        inputCompany.setId(1L);
        inputCompany.setName("aName");

        Company expectedCompany = new Company();
        expectedCompany.setId(1L);
        expectedCompany.setName("updated name");
        doReturn(expectedCompany).when(companyService).update(inputCompany);

        // WHEN
        mockMvc.perform(put("/secured/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputCompany))
        )
                .andExpect(status().isOk());
        // THEN
        verify(companyService).update(inputCompany);
    }

    @Test
    public void testGetByIdApi() throws Exception
    {
        // GIVEN
        long companyId = 100;
        Company expectedCompany = new Company();
        expectedCompany.setId(companyId);
        expectedCompany.setName("aName");
        doReturn(Optional.of(expectedCompany)).when(companyService).get(companyId);

        // WHEN
        mockMvc.perform(get("/secured/company/{id}", companyId))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:100,name:aName}"));
        // THEN
        verify(companyService).get(companyId);
    }

    @Test
    public void testGetByName() throws Exception
    {
        // GIVEN
        String companyName = "aName";
        Company expectedCompany = new Company();
        expectedCompany.setId(100L);
        expectedCompany.setName(companyName);
        doReturn(Optional.of(expectedCompany)).when(companyService).get(companyName);

        // WHEN
        mockMvc.perform(get("/secured/company").param("name", companyName))
                .andExpect(status().isOk())
                .andExpect(content().json("{id:100,name:aName}"));
        // THEN
        verify(companyService).get(companyName);
    }

    @Test
    public void testDeleteCompany() throws Exception
    {
        // GIVEN
        long companyId = 11L;
        doNothing().when(companyService).delete(companyId);

        // WHEN
        mockMvc.perform(delete("/secured/company/{id}", companyId))
                .andExpect(status().isOk());

        // THEN
        verify(companyService).delete(companyId);
    }
}