package ar.com.scholarship.Scholarship.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Year;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 15)
    private Integer cuil;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String address;

    @Column(nullable = false)
    private Year foundationYear;

    @Column(nullable = false, length = 15)
    private Integer contact;

    @ManyToOne
    @JoinColumn(name = "CompanyStatus_id")
    private CompanyStatus status;

    @OneToMany(mappedBy = "company")
    private Set<Manager> manager;

    @OneToMany(mappedBy = "company")
    private List<Course> courses;

}
