package ru.naumovweb.sitesstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.naumovweb.sitesstat.services.contracts.IUserService;

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

    @Autowired
    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody final RegisterDTO requestDto, final BindingResult binding) {

        if (binding.hasErrors()) {
            return ResponseEntity.badRequest().body(mapBindingToResource(binding));
        }

        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());

        User registeredUser = userService.register(user);
    }
}
