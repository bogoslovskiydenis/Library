package com.example.Library.service;

import com.example.Library.domain.User;
import com.example.Library.domain.UserPrincipal;
import com.example.Library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with username: " + s);
        }

        return new UserPrincipal(user);
    }
}
