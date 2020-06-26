package ar.com.scholarship.Scholarship.advices;

import ar.com.scholarship.Scholarship.exception.ApiErrorsResponseBody;
import ar.com.scholarship.Scholarship.exception.ApiFieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> exMessageLines = Arrays.asList(ex.getMessage().split("\n"));
        List<String> messageLines = Arrays.asList(exMessageLines.get(0).split(":"));
        String message = messageLines.get(messageLines.size() - 1).trim();
        List<String> filedLines = Arrays.asList(exMessageLines.get(1).split("\""));
        String field = filedLines.get(1);
        List<ApiFieldError> apiFieldErrors = Arrays.asList(new ApiFieldError(field, "", message));

        ApiErrorsResponseBody apiErrorsResponseBody = new ApiErrorsResponseBody<ApiFieldError>(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                apiFieldErrors
        );
        return ResponseEntity.badRequest().body(apiErrorsResponseBody);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ApiFieldError> apiFieldErrors = fieldErrors.stream().map(fieldError -> new ApiFieldError(
                fieldError.getField(),
                fieldError.getCode(),
                fieldError.getDefaultMessage()
        )).collect(Collectors.toList());

        ApiErrorsResponseBody apiErrorResponseBody = new ApiErrorsResponseBody<ApiFieldError>(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                apiFieldErrors);
        return ResponseEntity.badRequest().body(apiErrorResponseBody);
    }
}
