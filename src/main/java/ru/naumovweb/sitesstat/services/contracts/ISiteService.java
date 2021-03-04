package ru.naumovweb.sitesstat.services.contracts;

import ru.naumovweb.sitesstat.models.Site;
import ru.naumovweb.sitesstat.models.User;

/**
 * Service interface for class {@link Site}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */
public interface ISiteService {
    Site createForUser(User user, Site site);
}
