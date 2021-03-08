package ru.naumovweb.sitesstat.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.naumovweb.sitesstat.enums.PageDefinitionsEnum;

abstract public class BaseRepository {

    public Pageable getPageable(Integer limit, Integer offset, String sortBy, String sortDirection)
    {
        Pageable pageable = null;
        Sort sort = null;
        Integer pageNumber = 0;

        if (limit == null) { limit = PageDefinitionsEnum.LIMIT; }
        if (offset == null) { offset = PageDefinitionsEnum.OFFSET; }

        pageNumber = offset / limit;

        if (sortBy != null && sortDirection != null) {
            sort = Sort.by(sortBy);
            if (sortDirection.equals("desc")) {
                sort = sort.descending();
            }
        }

        if (sort != null) {
            pageable = PageRequest.of(pageNumber, limit, sort);
        } else {
            pageable = PageRequest.of(pageNumber, limit);
        }

        return pageable;
    }

}
