package ru.naumovweb.sitesstat.repositories.contracts;

import ru.naumovweb.sitesstat.models.Site;

public interface ISiteRepository {
    Site save(Site site);
}
