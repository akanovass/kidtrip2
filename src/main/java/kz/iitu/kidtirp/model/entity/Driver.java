package kz.iitu.kidtirp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "driver")
@Getter
@Setter
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private Date birthDate;

    private String area;

    private String photoUrl;

    private Integer experience;

    private Double salary;

    private Double salarySum;

    @OneToMany(mappedBy="driver")
    private List<Trip> trips;

    public Driver(User user, Date birthDate, String area, String photoUrl, Integer experience, Double salary, Double salarySum) {
        this.user = user;
        this.birthDate = birthDate;
        this.area = area;
        this.photoUrl = photoUrl;
        this.experience = experience;
        this.salary = salary;
        this.salarySum = salarySum;
    }
}
