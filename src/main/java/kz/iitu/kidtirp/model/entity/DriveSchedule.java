package kz.iitu.kidtirp.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "drive_schedule")
@Getter
@Setter
@NoArgsConstructor
public class DriveSchedule extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long childId;

    private String status;

    private Date driveDate;
}
