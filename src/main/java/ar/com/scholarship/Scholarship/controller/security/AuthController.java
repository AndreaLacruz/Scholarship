package ar.com.scholarship.Scholarship.controller.security;

import ar.com.scholarship.Scholarship.model.dto.security.JwtAuthRequestBody;
import ar.com.scholarship.Scholarship.model.dto.security.JwtAuthResponseBody;
import ar.com.scholarship.Scholarship.service.security.AuthServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Value("${auth.jwt.type}")
    private String authJwtType;

    @Autowired @Qualifier("authServices")
    private AuthServices authServices;

    @PostMapping ({"/login", "/login/"}) // http://localhost:8080/login
    public ResponseEntity createAuthToken(@Valid @RequestBody JwtAuthRequestBody body){
        LOGGER.info(body.toString());
        String token = authServices.jwtNewToken(body);
        JwtAuthResponseBody jwtAuthResponseBody = new JwtAuthResponseBody()
                .setToken(token)
                .setType(authJwtType);

        return ResponseEntity.ok(jwtAuthResponseBody);
    }
}
