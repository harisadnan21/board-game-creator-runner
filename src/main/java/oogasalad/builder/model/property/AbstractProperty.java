package oogasalad.builder.model.property;

import java.util.Objects;

/**
 * Abstract class that implements base methods of the Property Interface. This class is immutable,
 * as there is no way to modify its state once it has been created. We opted to create a class
 * instead of a record here in order to implement an inheritance hierarchy for properties (records
 * cannot extend other records/abstract classes).
 *
 * @param <T> The type of the property. Concrete classes should not use generic typing.
 *
 * @author Shaan Gondalia
 */
public abstract class AbstractProperty<T> implements Property<T>{

  private final String name;
  private final T value;

  /**
   * Creates a new Abstract Property with a name and generic value
   *
   * @param name the name of the property
   * @param value the value of the property
   */
  public AbstractProperty(String name, T value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Returns the name of the property
   *
   * @return the name of the property
   */
  public String name() {
    return name;
  }

  /**
   * Returns the value of the property
   *
   * @return the value of the property
   */
  public T value() {
    return value;
  }

  /**
   * Checks whether a property has the same name as another
   *
   * @param o The object to check equality against.
   * @return true if the objects are equal, false if not
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Property property = (Property) o;
    return name.equals(property.name());
  }

  /**
   * Checks whether a property has the same name and valueas another
   *
   * @param o The object to check equality against.
   * @return true if the objects are equal, false if not
   */
  public boolean fullEquals(Object o){
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Property property = (Property) o;
    return name.equals(property.name()) && value.equals(property.value());
  }

  /**
   * Hashes a property
   *
   * @return the hashcode of the property
   */
  @Override
  public int hashCode(){
    return Objects.hash(name, value);
  }

  /**
   * Returns the string representation of the properties value
   *
   * @return the string representation of the properties value
   */
  public abstract String valueAsString();

}
