package ru.naumovweb.sitesstat.services.contracts;

import ru.naumovweb.sitesstat.models.User;

/**
 * Service interface for class {@link User}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */
public interface IUserService {

    User register(User user);

    User findByEmail(String email);

}
