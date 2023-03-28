package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.Trip;
import kz.iitu.kidtirp.model.entity.Driver;
import kz.iitu.kidtirp.model.entity.Parent;
import kz.iitu.kidtirp.model.entity.User;
import kz.iitu.kidtirp.model.entity.enums.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findAllByDriver(Driver driver);
    List<Trip> findAllByDriverAndStatus(Driver driver, TripStatus status);
    List<Trip> findAllByParent(Parent parent);
    List<Trip> findAllByChild(User user);
}
