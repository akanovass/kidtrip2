package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
