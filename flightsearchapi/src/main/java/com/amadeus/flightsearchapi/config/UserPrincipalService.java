package com.amadeus.flightsearchapi.config;

import com.amadeus.flightsearchapi.entity.User;
import com.amadeus.flightsearchapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(username).orElseThrow(
                () -> new AuthenticationException("Authentication failed. User is not found." ) {});

        return  new UserPrincipal(user);
    }

}
