package ru.naumovweb.sitesstat.controllers;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all REST controllers
 *
 * @author Naumov Konstantin
 * @version 1.0
 */
public abstract class BaseRestController {

    protected Map<Object, Object> mapBindingToResource(final BindingResult binding) {
        Map<Object, Object> response = new HashMap<>();

        binding.getFieldErrors().forEach(fieldError -> {
            response.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return response;
    }

}
