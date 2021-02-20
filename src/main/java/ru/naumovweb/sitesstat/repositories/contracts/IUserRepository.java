package ru.naumovweb.sitesstat.repositories.contracts;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumovweb.sitesstat.models.User;

/**
 * Repository interface that extends {@link JpaRepository} for class {@link User}.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
