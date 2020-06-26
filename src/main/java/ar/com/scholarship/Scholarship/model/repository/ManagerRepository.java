package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("managerRepository")
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Modifying
    @Query(value = "ALTER TABLE Manager AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();

    Optional<Manager> findByDocumentation(Integer documentation);
}
