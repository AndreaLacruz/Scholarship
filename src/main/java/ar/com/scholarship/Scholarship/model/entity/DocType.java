package ar.com.scholarship.Scholarship.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity @Table(name = "DocType")
public class DocType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String type;

    @OneToMany(mappedBy = "docType")
    private Set<Manager> manager;

    public DocType(Long id) {
        this.id = id;
    }

    public DocType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public DocType setType(String type){
        this.type = type;
        return this;
    }

}
