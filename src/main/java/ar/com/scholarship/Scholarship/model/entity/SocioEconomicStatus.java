package ar.com.scholarship.Scholarship.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity @Table(name = "SocioEconomicStatus")
public class SocioEconomicStatus {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean areStudying;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean areWorking;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean hasIncome;

    @Column(nullable = false, length = 10)
    private Double monthlyIncome;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean hasKids;

    @Column(nullable = false, length = 10)
    private Integer howMany;

    @OneToOne
    @JoinColumn(name = "Student_id", referencedColumnName = "id")
    private Student student;

}
