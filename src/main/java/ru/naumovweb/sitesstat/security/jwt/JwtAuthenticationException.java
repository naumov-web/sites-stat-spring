package ru.naumovweb.sitesstat.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Authentication exception for SitesStat application.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
