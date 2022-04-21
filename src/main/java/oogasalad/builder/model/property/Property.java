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
   * Returns a property identical to this one, except with a different value
   *
   * @param newValue the value to give the new property
   * @return this new property with a different value
   */
  Property<T> withValue(T newValue);

  /**
   * Returns a property identical to this one, except with a different value
   *
   * @param newValue the value to give the new property as a String
   * @return this new property with a different value
   */
  Property<T> withValueAsString(String newValue);

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
