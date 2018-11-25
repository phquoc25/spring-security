package com.qph.resources;

import com.qph.services.CompanyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CompanyController.class)
public class CompanyControllerWithAdviceTest {
    @MockBean
    private CompanyService companyService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetByIdApi_ShouldReturn404StatusCode_WhenServiceReturnNull() throws Exception {
        // GIVEN
        doReturn(Optional.empty()).when(companyService).get(anyLong());

        // WHEN
        mockMvc.perform(get("/secured/company/{id}", 100L))
                .andExpect(status().isNotFound());
        // THEN
        verify(companyService).get(anyLong());
    }

    @Test
    public void testGetByNameApi_ShouldReturn404StatusCode_WhenServiceReturnNull() throws Exception {
        // GIVEN
        doReturn(Optional.empty()).when(companyService).get(anyString());

        // WHEN
        mockMvc.perform(get("/secured/company").param("name", "toto"))
                .andExpect(status().isNotFound());
        // THEN
        verify(companyService).get(anyString());
    }

    @Test
    public void testDeleteCompany_ShouldReturn404StatusCode_WhenServiceThrowsException() throws Exception {
        // GIVEN
        doThrow(new EmptyResultDataAccessException(1)).when(companyService).delete(anyLong());

        // WHEN
        mockMvc.perform(delete("/secured/company/{id}", 11L))
                .andExpect(status().isNotFound());
        // THEN
        verify(companyService).delete(anyLong());
    }
}