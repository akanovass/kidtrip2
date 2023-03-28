package kz.iitu.kidtirp.repository;

import kz.iitu.kidtirp.model.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
}
