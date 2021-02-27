package ru.naumovweb.sitesstat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.naumovweb.sitesstat.models.User;
import ru.naumovweb.sitesstat.security.jwt.JwtUser;
import ru.naumovweb.sitesstat.security.jwt.JwtUserFactory;
import ru.naumovweb.sitesstat.services.impl.UserService;

/**
 * Implementation of {@link UserDetailsService} interface for {@link JwtUser}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User with email: " + email + " not found");
        }

        return JwtUserFactory.create(user);
    }
}