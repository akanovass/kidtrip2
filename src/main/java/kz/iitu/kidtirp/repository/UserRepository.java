package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.Role;
import kz.iitu.kidtirp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    @Query(value = "Select u.* from users u join user_roles r on r.user_id = u.id where r.role_id = :roleId", nativeQuery = true)
    List<User> findAllByRoles(@Param("roleId") Long roleId);

}
