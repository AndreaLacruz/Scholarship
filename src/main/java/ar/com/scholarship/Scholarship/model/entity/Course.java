package ar.com.scholarship.Scholarship.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Time;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false, length = 20)
    private BigInteger price;

    @Column(nullable = false, length = 3)
    private Integer totalHours;

    @Column(nullable = false, length = 20)
    private Integer places;

    @Column(nullable = false, length = 20)
    private Integer scholarshipPlaces;

    @Column(nullable = false)
    private Integer scholarshipCounter;

    @Column(nullable = false)
    private Integer openPlacesCounter;

    @OneToMany(mappedBy = "course")
    private Set<StudentHasCourse> studentHasCourses;

    @ManyToOne
    @JoinColumn(name = "Modality_id")
    private Modality modality;

    @ManyToOne
    @JoinColumn(name = "CourseCategory_id")
    private CourseCategory category;

    @ManyToOne
    @JoinColumn(name = "Company_id")
    private Company company;

}
