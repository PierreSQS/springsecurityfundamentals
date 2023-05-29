package com.bharath.springcloud.security;

import com.bharath.springcloud.model.SecurityUser;
import com.bharath.springcloud.model.User;
import com.bharath.springcloud.repos.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userByEmailOpt = userRepo.findByEmail(email);

        return new SecurityUser(userByEmailOpt.orElseThrow(
                () -> new UsernameNotFoundException("User with email" + email + "not found!")));

    }
}
