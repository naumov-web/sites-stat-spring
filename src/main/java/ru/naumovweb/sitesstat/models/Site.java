package ru.naumovweb.sitesstat.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Simple domain object that represents site.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@Entity
@Table(name = "sites")
@Data
public class Site extends BaseModel {
    @Column(name = "name")
    private String name;

    @Column(name = "host")
    private String host;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
