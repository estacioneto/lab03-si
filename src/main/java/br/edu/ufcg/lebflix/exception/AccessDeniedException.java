package br.edu.ufcg.lebflix.exception;

/**
 * Created by estacio on 13/07/17.
 */
public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 4582682791089530231L;


    public AccessDeniedException(AccessDeniedMessage accessDeniedMessage) {
        super(accessDeniedMessage.getMessage());
    }
}
