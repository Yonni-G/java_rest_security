package fr.eni.ludotheque.exceptions;

public class EmailClientAlreadyExistException extends RuntimeException {

    public EmailClientAlreadyExistException() {
        super("Un client avec cet email existe déjà.");
    }
}
