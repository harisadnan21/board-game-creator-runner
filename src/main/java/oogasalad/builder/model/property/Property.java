package oogasalad.builder.model.property;

import java.util.Objects;

/**
 * Interface for any Generic Property. Properties have a name, and value
 *
 * @author Ricky Weerts and Shaan Gondalia
 */
public record Property(String name, String value) {

    /**
     * Checks equality between properties (same name and value)
     *
     * @param o The object to check equality against.
     * @return true if the objects are equal, false if not
     */
    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Property property = (Property) o;
        return name.equals(property.name) && value.equals(property.value);
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
    @Override
    public String toString() {
        return value;
    }
}
