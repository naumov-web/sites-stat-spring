package ru.naumovweb.sitesstat.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.naumovweb.sitesstat.models.Site;
import ru.naumovweb.sitesstat.repositories.contracts.ISiteBaseRepository;
import ru.naumovweb.sitesstat.repositories.contracts.ISiteRepository;

@Repository
public class SiteRepository implements ISiteRepository {

    private final ISiteBaseRepository baseRepository;

    @Autowired
    public SiteRepository(ISiteBaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public Site save(Site site) {
        baseRepository.save(site);
        return site;
    }
}
