package ar.com.scholarship.Scholarship.advices.security;

import ar.com.scholarship.Scholarship.exception.ApiErrorsResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

import java.nio.file.AccessDeniedException;
import java.util.Collections;

@RestControllerAdvice
public class RestSecurityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleBusinessLogicException(AccessDeniedException ex, NativeWebRequest req){

        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        ApiErrorsResponseBody apiErrorsResponseBody = new ApiErrorsResponseBody<>(
                httpStatus.value(),
                httpStatus.getReasonPhrase(),
                Collections.singletonList(ex.getMessage())
        );


        return ResponseEntity.status(httpStatus).body(apiErrorsResponseBody);
    }

}
