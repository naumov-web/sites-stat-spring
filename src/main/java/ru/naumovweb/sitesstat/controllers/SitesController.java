package ru.naumovweb.sitesstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.naumovweb.sitesstat.dto.requests.CreateSiteDto;
import ru.naumovweb.sitesstat.models.Site;
import ru.naumovweb.sitesstat.models.User;
import ru.naumovweb.sitesstat.services.contracts.ISiteService;
import ru.naumovweb.sitesstat.services.impl.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for site entity requests (create, list, update, delete)
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/account/sites")
public class SitesController extends BaseRestController {

    private final ISiteService siteService;

    private final UserService userService;

    @Autowired
    public SitesController(ISiteService siteService, UserService userService) {
        this.siteService = siteService;
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody final CreateSiteDto requestDto, final BindingResult binding) {

        if (binding.hasErrors()) {
            return ResponseEntity.badRequest().body(mapBindingToResource(binding));
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);

        Site site = new Site();
        site.setName(requestDto.getName());
        site.setHost(requestDto.getHost());

        site = siteService.createForUser(user, site);

        Map<Object, Object> response = new HashMap<>();
        response.put("id", site.getId());
        response.put("name", site.getName());
        response.put("host", site.getHost());

        return ResponseEntity.ok(response);
    }
}
