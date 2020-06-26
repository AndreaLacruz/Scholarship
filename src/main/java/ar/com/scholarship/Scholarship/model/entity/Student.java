package ar.com.scholarship.Scholarship.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity @Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, columnDefinition = "DATE")
    private Date birthday;

    @Column(nullable = false, length = 10)
    private String gender;

    @Column(nullable = false, length = 100)
    private String address;

    @OneToOne(mappedBy = "student")
    private SocioEconomicStatus socioEconomicStatus;

}
