package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.DocType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("docTypeRepository")
public interface DocTypeRepository extends JpaRepository<DocType,Long> {
    //Optional<DocType> findByType(String type);
}
