package ru.naumovweb.sitesstat.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Base class with properties 'id', 'created_at', 'updated_at'.
 * Used as a base class for all objects that requires this property.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@MappedSuperclass
@Data
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updated_at;
}
