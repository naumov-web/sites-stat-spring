package ru.naumovweb.sitesstat.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumovweb.sitesstat.dto.common.ListItemsDto;
import ru.naumovweb.sitesstat.models.Site;
import ru.naumovweb.sitesstat.models.User;
import ru.naumovweb.sitesstat.repositories.contracts.ISiteRepository;
import ru.naumovweb.sitesstat.services.contracts.ISiteService;

import java.util.Date;

@Service
public class SiteService implements ISiteService {

    private final ISiteRepository siteRepository;

    @Autowired
    public SiteService(ISiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public Site createForUser(User user, Site site) {
        site.setUser(user);
        site.setCreated_at(new Date());
        site.setUpdated_at(new Date());

        return siteRepository.save(site);
    }

    @Override
    public ListItemsDto<Site> indexForUser(
            User user,
            Integer limit,
            Integer offset,
            String sortBy,
            String sortDirection
    ) {
        return siteRepository.indexForUser(user, limit, offset, sortBy, sortDirection);
    }
}
