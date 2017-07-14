package br.edu.ufcg.lebflix.exception;

/**
 * Created by estacio on 13/07/17.
 */
public enum UnsupportedOperationMessage {

    EXISITING_USER("A user with this email already exists.");

    private String message;

    private UnsupportedOperationMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
