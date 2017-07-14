package br.edu.ufcg.lebflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by estacio on 13/07/17.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 4582682791089530231L;


    public AccessDeniedException(AccessDeniedMessage accessDeniedMessage) {
        super(accessDeniedMessage.getMessage());
    }
}
