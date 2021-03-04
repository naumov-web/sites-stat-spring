package ru.naumovweb.sitesstat.repositories.contracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.naumovweb.sitesstat.models.Site;

public interface ISiteBaseRepository extends JpaRepository<Site, Long>, JpaSpecificationExecutor<Site> { }
