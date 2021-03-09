package ru.naumovweb.sitesstat.repositories.contracts;

import ru.naumovweb.sitesstat.dto.common.ListItemsDto;
import ru.naumovweb.sitesstat.models.Site;
import ru.naumovweb.sitesstat.models.User;

import java.util.Optional;

public interface ISiteRepository {
    Site save(Site site);

    ListItemsDto<Site> indexForUser(User user, Integer limit, Integer offset, String sortBy, String sortDirection);

    Optional<Site> findByIdForUser(User user, Long id);
}
