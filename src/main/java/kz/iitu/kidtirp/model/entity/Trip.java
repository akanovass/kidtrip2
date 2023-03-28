package kz.iitu.kidtirp.model.entity;

import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "trip")
@NoArgsConstructor
@AllArgsConstructor
public class Trip extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String fromPlace;

    @Column
    private String toPlace;

    @Column
    private Timestamp startTime;

    @Column
    private Timestamp endTime;

    @Column
    private TripStatus status;

    @ManyToOne
    @JoinColumn(name="driver_id", nullable=false)
    private Driver driver;

    @OneToOne
    @JoinColumn(name="parent_id", nullable=false)
    private Parent parent;

    @OneToOne
    @JoinColumn(name="child_id", nullable=false)
    private User child;

    @Column
    private boolean rescheduled = false;

    @Column
    private boolean oneTimeDrive = false;

    public Trip(String fromPlace, String toPlace, Timestamp startTime, Timestamp endTime, TripStatus status, Driver driver, User passenger, Parent parent, boolean rescheduled) {
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.driver = driver;
        this.child = passenger;
        this.parent = parent;
        this.rescheduled = rescheduled;
    }
}
