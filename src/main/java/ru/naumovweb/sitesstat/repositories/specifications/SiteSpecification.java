package ru.naumovweb.sitesstat.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.naumovweb.sitesstat.models.Site;
import ru.naumovweb.sitesstat.models.User;

public class SiteSpecification {
    public static Specification<Site> filterByUser(User user) {
        return (root, query, cb) -> {
            return cb.equal(root.<String>get("user"), user);
        };
    }

    public static Specification<Site> filterByUserAndId(User user, Long id) {
        return (root, query, cb) -> {
            return cb.and(
                    cb.equal(root.<String>get("user"), user),
                    cb.equal(root.<String>get("id"), id)
            );
        };
    }
}
