package br.edu.ufcg.lebflix.exception;

/**
 * Created by estacio on 13/07/17.
 */
public enum AccessDeniedMessage {
    INEXISTENT_USER("User does not exist."),
    MISSING_AUTHORIZATION("Missing the authorization token."),
    DOESNT_OWN_THE_SERIES("This series is not on your profile or watchlist."),
    WRONG_EMAIL_OR_PASSWORD("Wrong email or password.");

    private String message;

    private AccessDeniedMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
