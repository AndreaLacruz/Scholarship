package ar.com.scholarship.Scholarship.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "StudentHasCourse")
public class StudentHasCourse {

    @EmbeddedId
    private StudentHasCourseId id;

    @ManyToOne
    @MapsId("student_Id")
    private Student student;

    @ManyToOne
    @MapsId("course_Id")
    private Course course;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.BooleanType")
    private Boolean courseHasFinalized;

    @ManyToOne
    @JoinColumn(name = "StudentStatus_id")
    private StudentStatus status;

    @ManyToOne
    @JoinColumn(name = "ApplicationType_id")
    private ApplicationType applicationType;

    public StudentHasCourse setId(StudentHasCourseId id){
        this.id = id;
        return this;
    }

    public StudentHasCourse setCourse(Course course){
        this.course = course;
        return this;
    }

    public StudentHasCourse setStudent(Student student){
        this.student = student;
        return this;
    }

}
