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

    public RuntimeException throwExceptionEntityNotFound(String entityName, StudentHasCourseId id) {
        ApiEntityError apiEntityError = new ApiEntityError(
                entityName,
                "NotFound",
                "The" + entityName + "with student id" + id.getStudentId() + " and course id " + id.getCourseId() + "does not exist"
        );
        return new BusinessLogicException(
                entityName + "does not exist",
                HttpStatus.NOT_FOUND,
                apiEntityError
        );
    }

    public RuntimeException throwExceptionApplicationAlreadyExists(StudentHasCourseId id) {
        ApiEntityError apiEntityError = new ApiEntityError(
                "applicationType",
                "ApplicationAlreadyExists",
                "The application for course id " + id.getCourseId() + " and student id "
                        + id.getStudentId() + "already exists"
        );
        return new BusinessLogicException(
                "this application already exists",
                HttpStatus.BAD_REQUEST,
                apiEntityError
        );
    }

    public RuntimeException throwExceptionCourseSoldOut(String courseName){
        ApiEntityError apiEntityError = new ApiEntityError(
                courseName, "Not Available", "No places available in this course" + courseName
        );
        return new BusinessLogicException("No places available in this course", HttpStatus.BAD_REQUEST, apiEntityError);
    }

    public RuntimeException throwExceptionNotAvailableCourse(){
        ApiEntityError apiEntityError = new ApiEntityError(
                "places",
                "Not Available",
                "The application to this course cant be made "
        ); return new BusinessLogicException(
                "This course is not available", HttpStatus.BAD_REQUEST, apiEntityError
        );

    }

    public RuntimeException throwExceptionScholarshipDenied() {
        ApiEntityError apiEntityError = new ApiEntityError(
                "applicationType",
                "ApplicationTypeDenied",
                "Missing Socio Economic Status from Student"
        );
        return new BusinessLogicException(
                "Scholarship denied",
                HttpStatus.BAD_REQUEST,
                apiEntityError
        );
    }
}

