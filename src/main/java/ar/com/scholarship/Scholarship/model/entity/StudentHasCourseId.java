package ar.com.scholarship.Scholarship.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class StudentHasCourseId implements Serializable {

    @Column(name = "Student_id")
    private Long studentId;

    @Column(name = "Course_id")
    private Long courseId;

    public StudentHasCourseId setStudentId(Long studentId){
        this.studentId = studentId;
        return this;
    }

    public StudentHasCourseId setCourseId(Long courseId){
        this.courseId = courseId;
        return this;
    }
}
