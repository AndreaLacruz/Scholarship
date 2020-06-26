package ar.com.scholarship.Scholarship.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ApiEntityError {
    private String entity, code, message;

    public ApiEntityError(String entity, String code, String message) {
        this.entity = entity;
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiEntityError{" +
                "entity='" + entity + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
