package ar.com.scholarship.Scholarship.components;


import ar.com.scholarship.Scholarship.exception.ApiEntityError;
import ar.com.scholarship.Scholarship.exception.BusinessLogicException;
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
}

