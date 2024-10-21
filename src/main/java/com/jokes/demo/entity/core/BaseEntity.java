package com.jokes.demo.entity.core;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Data
public abstract class BaseEntity<ID extends Serializable> implements Serializable, IEntity<ID> {

    @Column(name = "created",
            updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @Column(name = "modified")
    @UpdateTimestamp
    private Timestamp modified;

}
