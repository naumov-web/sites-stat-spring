package ru.naumovweb.sitesstat.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Simple domain object that represents user.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@Entity
@Table(name = "users")
@Data
public class User extends BaseModel {
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Site> sites;
}
