package ru.naumovweb.sitesstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
