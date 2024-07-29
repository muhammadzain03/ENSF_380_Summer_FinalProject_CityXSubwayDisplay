package ca.ucalgary.edu.ensf380.controller;

public class AdNotFoundException extends Exception {
    public AdNotFoundException(String message) {
        super(message);
    }

    public AdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
