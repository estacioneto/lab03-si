package br.edu.ufcg.lebflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by estacio on 17/07/17.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnsupportedOperationException extends java.lang.UnsupportedOperationException {
    public UnsupportedOperationException (String message) {
        super(message);
    }

    public UnsupportedOperationException (UnsupportedOperationMessage unsupportedOperationMessage) {
        super(unsupportedOperationMessage.getMessage());
    }
}
