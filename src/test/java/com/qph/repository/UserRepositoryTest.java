package com.qph.repository;

import com.qph.beans.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername() {
        User reader = userRepository.findByUsername("reader");
        Set<String> authorities = reader.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        assertEquals(Arrays.asList("COMPANY_READ", "DEPARTMENT_READ"), authorities);
    }
}