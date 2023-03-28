package kz.iitu.kidtirp.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
        })
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    private String fullName;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name="parent_id")
    private Parent parent;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
