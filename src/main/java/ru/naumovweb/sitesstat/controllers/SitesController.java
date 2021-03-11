package ru.naumovweb.sitesstat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.naumovweb.sitesstat.dto.common.ListItemsDto;
import ru.naumovweb.sitesstat.dto.requests.CreateSiteDto;
import ru.naumovweb.sitesstat.dto.requests.UpdateSiteDto;
import ru.naumovweb.sitesstat.dto.resources.ListResourceDto;
import ru.naumovweb.sitesstat.dto.resources.SiteDto;
import ru.naumovweb.sitesstat.enums.ResponseStatusCodesEnum;
import ru.naumovweb.sitesstat.models.Site;
import ru.naumovweb.sitesstat.models.User;
import ru.naumovweb.sitesstat.services.contracts.ISiteService;
import ru.naumovweb.sitesstat.services.impl.UserService;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping(value = "")
    public ResponseEntity index(
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "offset", required = false) Integer offset,
            @RequestParam(name = "sort_by", required = false) String sortBy,
            @RequestParam(name = "sort_direction", required = false) String sortDirection
    ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);

        ListItemsDto<Site> itemsDto = siteService.indexForUser(user, limit, offset, sortBy, sortDirection);

        return ResponseEntity.ok(
                (new ListResourceDto<Site>(itemsDto, SiteDto.class, Site.class)).getResponse()
        );
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody final UpdateSiteDto requestDto,
            final BindingResult binding
    ) {
        if (binding.hasErrors()) {
            return ResponseEntity.badRequest().body(mapBindingToResource(binding));
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);

        Optional<Site> site = siteService.findByIdForUser(user, id);

        if (!site.isPresent()) {
            return ResponseEntity.status(ResponseStatusCodesEnum.NOT_FOUND).body(null);
        }

        Site updatedSite = siteService.update(
                site.get(),
                requestDto.getName(),
                requestDto.getHost()
        );

        Map<Object, Object> response = new HashMap<>();
        response.put("id", updatedSite.getId());
        response.put("name", updatedSite.getName());
        response.put("host", updatedSite.getHost());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email);

        Optional<Site> site = siteService.findByIdForUser(user, id);

        if (!site.isPresent()) {
            return ResponseEntity.status(ResponseStatusCodesEnum.NOT_FOUND).body(null);
        }

        siteService.delete(site.get().getId());

        Map<Object, Object> response = new HashMap<>();
        response.put("success", true);

        return ResponseEntity.ok(null);
    }
}
