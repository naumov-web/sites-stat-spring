package ru.naumovweb.sitesstat.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.naumovweb.sitesstat.dto.common.ListItemsDto;
import ru.naumovweb.sitesstat.models.Site;
import ru.naumovweb.sitesstat.models.User;
import ru.naumovweb.sitesstat.repositories.BaseRepository;
import ru.naumovweb.sitesstat.repositories.contracts.ISiteBaseRepository;
import ru.naumovweb.sitesstat.repositories.contracts.ISiteRepository;
import ru.naumovweb.sitesstat.repositories.specifications.SiteSpecification;

import java.util.Optional;

@Repository
public class SiteRepository extends BaseRepository implements ISiteRepository {

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

    @Override
    public ListItemsDto<Site> indexForUser(User user, Integer limit, Integer offset, String sortBy, String sortDirection) {
        ListItemsDto<Site> dto = new ListItemsDto<>();
        Pageable pageable = getPageable(limit, offset, sortBy, sortDirection);

        Page<Site> pageDto = baseRepository.findAll(SiteSpecification.filterByUser(user), pageable);

        dto.setItems(pageDto.getContent());
        dto.setCount(pageDto.getTotalElements());

        return dto;
    }

    @Override
    public Optional<Site> findByIdForUser(User user, Long id) {
        return baseRepository.findOne(SiteSpecification.filterByUserAndId(user, id));
    }
}
