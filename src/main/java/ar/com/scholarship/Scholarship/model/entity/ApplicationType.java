package ar.com.scholarship.Scholarship.model.entity;

import ar.com.scholarship.Scholarship.model.dto.ApplicationTypeDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity @Table(name = "ApplicationType")
public class ApplicationType {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    private String type;

    @OneToMany(mappedBy = "applicationType")
    private Set<StudentHasCourse> studentHasCourses;

    public ApplicationType(String type) {
        this.type = type;
    }

}
