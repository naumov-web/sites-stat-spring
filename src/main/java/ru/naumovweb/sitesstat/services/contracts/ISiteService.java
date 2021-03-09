package ru.naumovweb.sitesstat.services.contracts;

import ru.naumovweb.sitesstat.dto.common.ListItemsDto;
import ru.naumovweb.sitesstat.models.Site;
import ru.naumovweb.sitesstat.models.User;

import java.util.Optional;

/**
 * Service interface for class {@link Site}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */
public interface ISiteService {
    Site createForUser(User user, Site site);

    ListItemsDto<Site> indexForUser(User user, Integer limit, Integer offset, String sortBy, String sortDirection);

    Optional<Site> findByIdForUser(User user, Long id);

    Site update(Site site, String name, String host);
}
