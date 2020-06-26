package ar.com.scholarship.Scholarship.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Manager")
public class Manager implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 20)
    private Integer documentation;

    @Column(nullable = false, length = 100)
    private String position;

    @Column(nullable = false, length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "DocType_id")
    private DocType docType;

    @ManyToOne
    @JoinColumn(name = "Company_id")
    private Company company;

}
