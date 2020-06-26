package ar.com.scholarship.Scholarship.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity @Table(name = "CourseCategory")
public class CourseCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String category;

    @OneToMany(mappedBy = "category")
    private List<Course> courses;

    public CourseCategory(Long id, String category) {
        this.id = id;
        this.category = category;
    }
}
