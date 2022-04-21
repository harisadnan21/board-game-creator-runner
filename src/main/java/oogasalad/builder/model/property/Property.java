package oogasalad.builder.model.property;


/**
 * Interface for any Generic Property. Properties have a name, and value.
 * <p>
 * We opted to create an interface instead of a record here in order to implement an inheritance
 * hierarchy for properties (records cannot extend other records/abstract classes).
 *
 * @param <T> The type of the property. Concrete classes should not use generic typing.
 * @author Ricky Weerts and Shaan Gondalia
 */
public interface Property<T> {

  /**
   * Checks whether a property has the same name as another
   *
   * @param o The object to check equality against.
   * @return true if the objects are equal, false if not
   */

  boolean equals(Object o);

  /**
   * Checks whether a property has the same name and valueas another
   *
   * @param o The object to check equality against.
   * @return true if the objects are equal, false if not
   */
  boolean fullEquals(Object o);

  /**
   * Hashes a property
   *
   * @return the hashcode of the property
   */
  int hashCode();

  /**
   * Gets the actual property name, without the required- and namespace markers
   *
   * @return the actual property name
   */
  default String shortName() {
    String[] nameParts = name().split("-");
    return nameParts[nameParts.length - 1];
  }

  /**
   * Returns the name of the property
   *
   * @return the name of the property
   */
  String name();

  /**
   * Returns the form of the property
   *
   * @return the form of the property
   */
  String form();

  /**
   * Returns the value of the property
   *
   * @return the value of the property
   */
  T value();

  /**
   * Returns the default value of the property
   *
   * @return the default value of the property
   */
  T defaultValue();

  /**
   * Returns an instance of this property but with the information specified
   *
   * @param name the name of the new property
   * @param value the value of the new property as a string
   * @param defaultValue the default value of the new property as a string
   * @param form the form of the new property
   * @return a new property of the same type but with the provided information instead
   */
  Property<T> with(String name, String value, String defaultValue, String form);

  /**
   * Returns an instance of this property but with the information specified
   *
   * @param name the name of the new property
   * @param value the value of the new property as a string
   * @param defaultValue the default value of the new property as a string
   * @return a new property of the same type but with the provided information instead
   */
  default Property<T> with(String name, String value, String defaultValue) {
    return with(name, value, defaultValue, form());
  }

  /**
   * Returns an instance of this property but with the specified name and value
   *
   * @param name the name of the new property
   * @param value the value of the new property as a string
   * @return a new property of the same type but with the provided name and value instead
   */
  default Property<T> with(String name, String value) {
    return with(name, value, defaultValueAsString());
  }

  /**
   * Returns an instance of this property but with the specified value
   *
   * @param value the value of the new property as a string
   * @return a new property of the same type but with the provided value instead
   */
  default Property<T> with(String value) {
    return with(name(), value);
  }

  /**
   * Returns a property identical to this one, except with a different value
   * In contrast to `with()`, the value is provided as an actual type rather than a string
   *
   * @param newValue the value to give the new property
   * @return this new property with a different value
   */
  Property<T> withValue(T newValue);

  /**
   * Returns the string representation of the property's default value
   *
   * @return the string representation of the property's default value
   */
  String defaultValueAsString();

  /**
   * Returns the string representation of the property's value
   *
   * @return the string representation of the property's value
   */
  String valueAsString();
}
