package oogasalad.builder.model.exception;

public class IllegalPropertyDefinitionException extends RuntimeException {
    public IllegalPropertyDefinitionException(String msg) {
        super(msg, null);
    }

    public IllegalPropertyDefinitionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
