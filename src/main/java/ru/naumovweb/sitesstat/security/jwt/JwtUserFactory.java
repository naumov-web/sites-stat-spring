package ru.naumovweb.sitesstat.security.jwt;

import ru.naumovweb.sitesstat.models.User;

import java.util.ArrayList;

/**
 * Implementation of Factory Method for class {@link JwtUser}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */
public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
