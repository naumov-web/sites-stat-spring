package ru.naumovweb.sitesstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.naumovweb.sitesstat.dto.requests.LoginDto;
import ru.naumovweb.sitesstat.dto.requests.RegisterDto;
import ru.naumovweb.sitesstat.models.User;
import ru.naumovweb.sitesstat.security.jwt.JwtTokenProvider;
import ru.naumovweb.sitesstat.services.contracts.IUserService;

import javax.validation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for authentication requests (register, login)
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthController extends BaseRestController {
    private final IUserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(IUserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody final RegisterDto requestDto, final BindingResult binding) {

        if (binding.hasErrors()) {
            return ResponseEntity.badRequest().body(mapBindingToResource(binding));
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());

        User registeredUser = userService.register(user);

        String token = jwtTokenProvider.createToken(registeredUser.getEmail());

        Map<Object, Object> response = new HashMap<>();
        response.put("email", registeredUser.getEmail());
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("login")
    public ResponseEntity login(@Valid @RequestBody final LoginDto requestDto, final BindingResult binding) {

        if (binding.hasErrors()) {
            return ResponseEntity.badRequest().body(mapBindingToResource(binding));
        }

        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
            User user = userService.findByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(user.getEmail());

            Map<Object, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("email", user.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
