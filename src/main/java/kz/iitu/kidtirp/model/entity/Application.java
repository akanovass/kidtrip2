package kz.iitu.kidtirp.model.entity;


import kz.iitu.kidtirp.model.entity.enums.ApplicationStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "application")
@Getter
@Setter
@NoArgsConstructor
public class Application extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long driverId;

    private ApplicationStatus status;


}
