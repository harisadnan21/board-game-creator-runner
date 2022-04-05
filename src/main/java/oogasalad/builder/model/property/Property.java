package oogasalad.builder.model.property;

import java.util.*;
import oogasalad.builder.controller.ExceptionResourcesSingleton;

/**
 * An immutable class that stores a value of a specified type as a String under a given name
 */
public record Property(Class type, String name, String value) {

    // Implemented as static method rather than constructor because constructor conflicts with the one made by the record
    public static <T> Property newInstance(Class<T> type, String name, T value) {
        checkValueType(value, type);
        return new Property(type, name, Objects.requireNonNull(value).toString());
    }

    public Property withValue(Object newValue) {
        Objects.requireNonNull(newValue);
        checkValueType(newValue, type);
        return withValueAsString(newValue.toString());
    }

    public Property withValueAsString(String newValue) {
        Objects.requireNonNull(newValue);
        return new Property(type, name, newValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Property property = (Property) o;
        return type.equals(property.type) && name.equals(property.name);
    }

    public boolean fullEquals(Object o) {
        return equals(o) && ((Property)o).value.equals(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    private static void checkValueType(Object value, Class<?> type) {
        if(value.getClass() != type) {
            throw new IllegalArgumentException(
                ExceptionResourcesSingleton.getInstance().getString("BadPropertyNewValue", type.getName()));
        }
    }
}