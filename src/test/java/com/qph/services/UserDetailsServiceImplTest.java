package com.qph.services;

import com.qph.beans.Authority;
import com.qph.beans.User;
import com.qph.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {
    private UserDetailsService userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void loadUserByUsername() {
        // GIVEN
        String password = "pwd";
        String username = "username";
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);

        Set<Authority> authorities = new HashSet<>();
        Authority comReadAuthority = new Authority();
        comReadAuthority.setId(1);
        comReadAuthority.setAuthority("COMPANY_READ");

        Authority depmtAuthority = new Authority();
        depmtAuthority.setId(2);
        depmtAuthority.setAuthority("DEPARTMENT_READ");

        authorities.add(comReadAuthority);
        authorities.add(depmtAuthority);

        user.setAuthorities(authorities);
        doReturn(user).when(userRepository).findByUsername(username);

        // WHEN
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // THEN
        verify(userRepository).findByUsername(username);
        assertEquals(password, userDetails.getPassword());
        assertEquals(username, userDetails.getUsername());
        Set<String> actualAuths = userDetails.getAuthorities().stream().map(o -> o.getAuthority()).collect(Collectors.toSet());
        assertTrue(actualAuths.contains("COMPANY_READ"));
        assertTrue(actualAuths.contains("DEPARTMENT_READ"));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsername_whenUserNotFound_shouldThrowsException() {
        // GIVEN
        String username = "username";
        doReturn(null).when(userRepository).findByUsername(username);

        // WHEN
        userDetailsService.loadUserByUsername(username);

        // THEN
        verify(userRepository).findByUsername(username);
    }
}