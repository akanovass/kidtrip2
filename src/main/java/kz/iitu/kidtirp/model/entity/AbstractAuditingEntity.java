package kz.iitu.kidtirp.model.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Base abstract class for entities which will hold definitions for created, last modified by and created,
 * last modified by date.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class AbstractAuditingEntity {

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    private String createdBy = "system";

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    @Column(name = "deleted_by", length = 50)
    private String deletedBy;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;
}
