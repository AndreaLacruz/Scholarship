package ar.com.scholarship.Scholarship.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "StudentStatus")
public class StudentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Aprobado, rechazado, pendiente
    @Column(nullable = false, length = 20)
    private String status;

    @OneToMany(mappedBy = "status")
    private Set<StudentHasCourse> studentHasCourses;

}
