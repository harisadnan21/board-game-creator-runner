package oogasalad.builder.view.tab;

import oogasalad.builder.controller.ExceptionResourcesSingleton;

/**
 * This exception is thrown when an error occurs while creating an element in GameElementTab.
 *
 * @author Shaan Gondalia
 * @author Ricky Weerts
 */
public class ElementCreationException extends RuntimeException {
  private static final String DEFAULT_MESSAGE_KEY = "ElementCreationException";

  /**
   * Creates new ElementCreationException for a specified element type
   *
   * @param elementType the type of the element that was being created
   */
  public ElementCreationException(String elementType) {
    this(elementType, null);
  }

  /**
   * Creates new ElementCreationException for a specific element type and with a provided cause
   *
   * @param elementType the type of the element that was being created.
   * @param cause the throwable that caused this exception
   */
  public ElementCreationException(String elementType, Throwable cause) {
    super(ExceptionResourcesSingleton.getInstance().getString(DEFAULT_MESSAGE_KEY, elementType), cause);
  }
}
