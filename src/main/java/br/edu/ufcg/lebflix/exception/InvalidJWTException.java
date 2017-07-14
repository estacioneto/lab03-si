package br.edu.ufcg.lebflix.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by estacio on 14/07/17.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class InvalidJWTException extends JWTVerificationException {
    public InvalidJWTException(String message) {
        super(message);
    }
}
