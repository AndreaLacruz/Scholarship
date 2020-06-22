package ar.com.scholarship.Scholarship.components;


import ar.com.scholarship.Scholarship.exception.ApiEntityError;
import ar.com.scholarship.Scholarship.exception.BusinessLogicException;
import ar.com.scholarship.Scholarship.model.entity.StudentHasCourseId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component("businessLogicExceptionComponent")
public class BusinessLogicExceptionComponent {

    public RuntimeException throwExceptionEntityNotFound(String entityName, Long id){
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName,
                "NotFound",
                "The" + entityName + "with id" + id + "does not exist"
        );

        return new BusinessLogicException(
                entityName + "does not exist",
                HttpStatus.NOT_FOUND,
                apiEntityError
        );
    }

    public RuntimeException throwExceptionCourseSoldOut(String courseName){
        ApiEntityError apiEntityError = new ApiEntityError(
                courseName, "Not Available", "No places available in this course" + courseName
        );
        return new BusinessLogicException("No places available in this course", HttpStatus.BAD_REQUEST, apiEntityError);
    }

    public RuntimeException throwExceptionApplicationAlreadyExist(StudentHasCourseId id){
        ApiEntityError apiEntityError = new ApiEntityError(
                "StudentHasCourse",
                "Application Already Exist",
                "The application to this course already exist " + id.getCourseId() + " on this student " + id.getStudentId()
        ); return new BusinessLogicException(
                "This application already exist", HttpStatus.BAD_REQUEST, apiEntityError
        );

    }
}

