package com.blog.app.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.GenerationType.AUTO;

@MappedSuperclass
@Getter
@Setter
public abstract class IdentifiableEntity {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column
    private LocalDateTime lastUpdateDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final IdentifiableEntity identifiableEntity = (IdentifiableEntity) o;
        if (((IdentifiableEntity) o).getId() == null || this.getId() == null)
            return false;
        return identifiableEntity.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
