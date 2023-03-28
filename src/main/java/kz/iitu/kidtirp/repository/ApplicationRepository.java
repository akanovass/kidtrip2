package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
