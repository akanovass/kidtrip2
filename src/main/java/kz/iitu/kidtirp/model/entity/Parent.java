package kz.iitu.kidtirp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "parent")
@Getter
@Setter
@NoArgsConstructor
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="tariff_id")
    private TariffPlans tariff;

    @OneToMany(mappedBy = "parent")
    private List<User> children;

    @OneToOne
    @JoinColumn(name="driver_id")
    private Driver driver;

}
