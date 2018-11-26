package com.qph.repository;

import com.qph.beans.Authority;
import com.qph.beans.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
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
        // GIVEN
        Set<Authority> authorities = new HashSet<>();
        Authority comReadAuthority = new Authority();
        comReadAuthority.setId(1);
        comReadAuthority.setAuthority("COMPANY_READ");

        Authority depmtAuthority = new Authority();
        depmtAuthority.setId(2);
        depmtAuthority.setAuthority("DEPARTMENT_READ");

        authorities.add(comReadAuthority);
        authorities.add(depmtAuthority);

        String username = "reader";
        String pass = "apwd";
        User user = new User();
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setUsername(username);
        user.setPassword(pass);
        user.setAuthorities(authorities);
        userRepository.save(user);

        // WHEN
        User reader = userRepository.findByUsername(username);
        Set<String> actualAuthorities = reader.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        // THEN
        Set<String> expectedAuth = new HashSet<>();
        expectedAuth.add("COMPANY_READ");
        expectedAuth.add("DEPARTMENT_READ");
        assertEquals(expectedAuth, actualAuthorities);
        assertEquals(pass, reader.getPassword());
    }
}