package oogasalad.engine.model.parser.exception;

/**
 * Parsing exception for when parameter expressions do not conform to expected format
 * @author Jake Heller
 */
public class ParameterFormatException extends RuntimeException {

  // TODO: Replace hardcoded exception message with properties file
  private static final String DEFAULT_MESSAGE = "Incorrectly formatted parameter expression";

  /**
   * Creates new ParameterFormatException with the default message
   */
  public ParameterFormatException() {
    super(DEFAULT_MESSAGE);
  }

  /**
   * Creates new ParameterFormatException with the provided message
   *
   * @param message the error message for this exception
   */
  public ParameterFormatException(String message) {
    super(message);
  }
}
