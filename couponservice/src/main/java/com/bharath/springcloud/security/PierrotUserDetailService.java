package com.bharath.springcloud.security;

import com.bharath.springcloud.model.User;
import com.bharath.springcloud.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PierrotUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    public PierrotUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User foundUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found!"));

        // seems also to validate the password
        return new org.springframework.security.core.userdetails.User(foundUser.getEmail(),
                                                                      foundUser.getPassword(),
                                                                      foundUser.getRoles());
    }
}
