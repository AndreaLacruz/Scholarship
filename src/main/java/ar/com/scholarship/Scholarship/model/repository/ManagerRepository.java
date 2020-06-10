package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("managerRepository")
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByDni(Integer documentation);
}
