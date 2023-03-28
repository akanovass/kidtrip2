package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.TariffPlans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffPlansRepository extends JpaRepository<TariffPlans, Long> {
}
