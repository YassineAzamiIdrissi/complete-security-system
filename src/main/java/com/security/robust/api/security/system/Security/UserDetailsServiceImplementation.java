package com.security.robust.api.security.system.Security;

import com.security.robust.api.security.system.CustomExceptions.UserNotFoundException;
import com.security.robust.api.security.system.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepo.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User with email " + email + " isn't found"));
    }
}
