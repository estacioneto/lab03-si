package br.edu.ufcg.lebflix.exception;

/**
 * Created by estacio on 13/07/17.
 */
public enum AccessDeniedMessage {
    INEXISTENT_USER("User does not exist."),
    MISSING_AUTHORIZATION("Missing the authorization token");

    private String message;

    private AccessDeniedMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
