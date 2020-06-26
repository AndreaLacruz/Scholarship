package ar.com.scholarship.Scholarship.model.repository;

import ar.com.scholarship.Scholarship.model.entity.DocType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("docTypeRepository")
public interface DocTypeRepository extends JpaRepository<DocType,Long> {

    @Modifying
    @Query(value = "ALTER TABLE Doc_Type AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoincrementValue();

}
