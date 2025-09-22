package br.gov.agu.nutec.mscontroledeusuario.exception.hanlder;

import br.gov.agu.nutec.mscontroledeusuario.exception.AuthenticationException;
import br.gov.agu.nutec.mscontroledeusuario.exception.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardError> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        StandardError error = new StandardError(
                401,
                ex.getMessage(),
                "Authentication Error",
                Instant.now(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }




}
